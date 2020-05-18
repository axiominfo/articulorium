package com.articulorum.web.service;

import static com.articulorum.web.utility.PathUtility.SLASH;
import static java.lang.String.join;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.removeEnd;
import static org.apache.commons.lang3.StringUtils.removeStart;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentServletMapping;

import java.util.Optional;

import com.articulorum.domain.Container;
import com.articulorum.domain.Element;
import com.articulorum.domain.repo.ContainerRepo;
import com.articulorum.web.producer.ContainerProducer;
import com.articulorum.web.utility.LdpPredicate;
import com.articulorum.web.utility.RdfType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaContainerService implements ContainerService {

    @Autowired
    private ContainerRepo containerRepo;

    @Autowired
    private ContainerProducer eventProducer;

    @Override
    public boolean existsByPath(String path) {
        return containerRepo.existsByPath(path);
    }

    @Override
    public Optional<Container> findByPath(String path) {
        return containerRepo.findByPath(path);
    }

    @Override
    public String create(Container parent, Container container, String uri) {

        Optional<Element> parentDirectContainer = parent.getElementByAttribute(LdpPredicate.DIRECT_CONTAINER);

        Optional<Element> parentMembershipResource = parent.getElementByAttribute(LdpPredicate.MEMBERSHIP_RESOURCE);
        Optional<Element> parentHasMemberRelation = parent.getElementByAttribute(LdpPredicate.HAS_MEMBER_RELATION);

        if (parentDirectContainer.isPresent() && parentMembershipResource.isPresent() && parentHasMemberRelation.isPresent()) {

            String memberObject = parentMembershipResource.get().getValue();

            String baseUri = fromCurrentServletMapping().toUriString();

            Optional<Container> memberContainer = containerRepo.findByPath(removeStart(memberObject.replace(baseUri, EMPTY), SLASH));

            if (memberContainer.isPresent()) {
                String memberAttribute = parentHasMemberRelation.get().getValue();

                Optional<Element> parentIndirectContainer = parent.getElementByAttribute(LdpPredicate.INDIRECT_CONTAINER);

                Optional<Element> parentInsertedContentRelation = parent.getElementByAttribute(LdpPredicate.INSERTED_CONTENT_RELATION);

                if (parentIndirectContainer.isPresent() && parentInsertedContentRelation.isPresent()) {
                    String memberValue = parentInsertedContentRelation.get().getValue();
                    memberContainer.get().getElements().add(new Element(memberAttribute, memberValue));
                } else {
                    memberContainer.get().getElements().add(new Element(memberAttribute, uri));
                }

                // update
                Container updatedContainer = containerRepo.save(memberContainer.get());

                eventProducer.sendUpdated(updatedContainer);
            }
        }

        Optional<Element> membershipResource = container.getElementByAttribute(LdpPredicate.MEMBERSHIP_RESOURCE);
        Optional<Element> hasMemberRelation = container.getElementByAttribute(LdpPredicate.HAS_MEMBER_RELATION);

        if (membershipResource.isPresent() && hasMemberRelation.isPresent()) {
            Optional<Element> insertedContentRelation = container.getElementByAttribute(LdpPredicate.INSERTED_CONTENT_RELATION);
            if (insertedContentRelation.isPresent()) {
                container.getElements().add(RdfType.INDIRECT_CONTAINER);
            } else {
                container.getElements().add(RdfType.DIRECT_CONTAINER);
            }
        } else {
            container.getElements().add(RdfType.BASIC_CONTAINER);
        }

        container.getElements().add(RdfType.CONTAINER);
        container.getElements().add(RdfType.RDF_SOURCE);

        parent.getElements().add(new Element(LdpPredicate.CONTAINS, uri));

        // update
        Container updatedContainer = containerRepo.save(parent);
        eventProducer.sendUpdated(updatedContainer);

        // create
        Container createdContainer = containerRepo.save(container);
        eventProducer.sendCreated(createdContainer);

        return uri;
    }

    @Override
    public void delete(Container parent, Container container, String uri) {

        String baseUri = fromCurrentServletMapping().toUriString();

        container.getElementsByAttribute(LdpPredicate.CONTAINS).stream()
            .map(element -> containerRepo.findByPath(removeStart(element.getValue().replace(baseUri, EMPTY), SLASH)))
            .filter(child -> child.isPresent())
            .map(child -> child.get())
            .forEach(child -> delete(container, child, join(SLASH, removeEnd(baseUri, SLASH), child.getPath())));

        Optional<Element> parentDirectContainer = parent.getElementByAttribute(LdpPredicate.DIRECT_CONTAINER);

        Optional<Element> parentMembershipResource = parent.getElementByAttribute(LdpPredicate.MEMBERSHIP_RESOURCE);
        Optional<Element> parentHasMemberRelation = parent.getElementByAttribute(LdpPredicate.HAS_MEMBER_RELATION);

        if (parentDirectContainer.isPresent() && parentMembershipResource.isPresent() && parentHasMemberRelation.isPresent()) {

            String memberObject = parentMembershipResource.get().getValue();

            Optional<Container> memberContainer = containerRepo.findByPath(removeStart(memberObject.replace(baseUri, EMPTY), SLASH));

            if (memberContainer.isPresent()) {
                String memberAttribute = parentHasMemberRelation.get().getValue();

                Optional<Element> parentIndirectContainer = parent.getElementByAttribute(LdpPredicate.INDIRECT_CONTAINER);

                Optional<Element> parentInsertedContentRelation = parent.getElementByAttribute(LdpPredicate.INSERTED_CONTENT_RELATION);

                if (parentIndirectContainer.isPresent() && parentInsertedContentRelation.isPresent()) {
                    String memberValue = parentInsertedContentRelation.get().getValue();
                    memberContainer.get().getElements().remove(new Element(memberAttribute, memberValue));
                } else {
                    memberContainer.get().getElements().remove(new Element(memberAttribute, uri));
                }

                // update
                Container updatedContainer = containerRepo.save(memberContainer.get());
                eventProducer.sendUpdated(updatedContainer);
            }
        }

        parent.getElements().remove(new Element(LdpPredicate.CONTAINS, uri));

        // update
        Container updatedContainer = containerRepo.save(parent);
        eventProducer.sendUpdated(updatedContainer);

        // delete
        containerRepo.delete(container);
        eventProducer.sendDeleted(container);
    }

}