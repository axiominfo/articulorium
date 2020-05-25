package com.articulorum.triplestore.consumer;

import com.articulorum.domain.Container;
import com.articulorum.domain.Element;
import com.bigdata.rdf.sail.remote.BigdataSailRemoteRepository;
import com.bigdata.rdf.sail.remote.BigdataSailRemoteRepositoryConnection;
import com.bigdata.rdf.sail.webapp.client.RemoteRepository;
import com.bigdata.rdf.sail.webapp.client.RemoteRepositoryManager;

import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.impl.LiteralImpl;
import org.openrdf.model.impl.StatementImpl;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.repository.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ContainerConsumer {

    @Autowired
    private RemoteRepositoryManager remoteRepositoryManager;

    @JmsListener(destination = "topic.created")
    public void receiveCreated(@Payload Container container) {
        System.out.println("created: " + container);

        RemoteRepository remoteRepository = remoteRepositoryManager.getRepositoryForNamespace("test");
        BigdataSailRemoteRepository repo = remoteRepository.getBigdataSailRemoteRepository();
        try {
            repo.initialize();
            BigdataSailRemoteRepositoryConnection connection = repo.getConnection();

            connection.begin();

            Resource s = new URIImpl("http://localhost:8080/" + container.getPath());
            for (Element element : container.getElements()) {
                URI p = new URIImpl(element.getAttribute());
                Value o = new LiteralImpl(element.getValue());
                Statement stmt = new StatementImpl(s, p, o);
                connection.add(stmt);
            }

            connection.commit();

            connection.close();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @JmsListener(destination = "topic.updated")
    public void receiveUpdated(@Payload Container container) {
        System.out.println("updated: " + container);


        RemoteRepository remoteRepository = remoteRepositoryManager.getRepositoryForNamespace("test");
        BigdataSailRemoteRepository repo = remoteRepository.getBigdataSailRemoteRepository();
        try {
            repo.initialize();
            BigdataSailRemoteRepositoryConnection connection = repo.getConnection();

            connection.begin();

            Resource s = new URIImpl("http://localhost:8080/" + container.getPath());

            connection.clear(s);

            for (Element element : container.getElements()) {
                URI p = new URIImpl(element.getAttribute());
                Value o = new LiteralImpl(element.getValue());
                Statement stmt = new StatementImpl(s, p, o);
                connection.add(stmt);
            }

            connection.commit();

            connection.close();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @JmsListener(destination = "topic.deleted")
    public void receiveDeleted(@Payload Container container) {
        System.out.println("deleted: " + container);

        RemoteRepository remoteRepository = remoteRepositoryManager.getRepositoryForNamespace("test");
        BigdataSailRemoteRepository repo = remoteRepository.getBigdataSailRemoteRepository();
        try {
            repo.initialize();
            BigdataSailRemoteRepositoryConnection connection = repo.getConnection();

            connection.begin();

            Resource s = new URIImpl("http://localhost:8080/" + container.getPath());

            connection.clear(s);

            connection.commit();

            connection.close();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

}