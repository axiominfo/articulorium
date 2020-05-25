package com.articulorum.triplestore.consumer;

import com.articulorum.domain.Container;
import com.articulorum.domain.Element;
import com.articulorum.triplestore.service.RemoteRepositoryService;
import com.bigdata.rdf.sail.remote.BigdataSailRemoteRepositoryConnection;

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
    private RemoteRepositoryService remoteRepositoryService;

    @JmsListener(destination = "topic.created")
    public void receiveCreated(@Payload Container container) {
        System.out.println("created: " + container);
        try {
            BigdataSailRemoteRepositoryConnection connection = remoteRepositoryService.getConnection();
            connection.begin();
            addStatements(connection, container);
            connection.commit();
            connection.close();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @JmsListener(destination = "topic.updated")
    public void receiveUpdated(@Payload Container container) {
        System.out.println("updated: " + container);
        try {
            BigdataSailRemoteRepositoryConnection connection = remoteRepositoryService.getConnection();
            connection.begin();
            addStatements(connection, container, true);
            connection.commit();
            connection.close();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @JmsListener(destination = "topic.deleted")
    public void receiveDeleted(@Payload Container container) {
        System.out.println("deleted: " + container);
        try {
            BigdataSailRemoteRepositoryConnection connection = remoteRepositoryService.getConnection();
            connection.begin();
            connection.clear(new URIImpl("http://localhost:8080/" + container.getPath()));
            connection.commit();
            connection.close();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    private void addStatements(BigdataSailRemoteRepositoryConnection connection, Container container) throws RepositoryException {
        addStatements(connection, container, false);
    }

    private void addStatements(BigdataSailRemoteRepositoryConnection connection, Container container, boolean clear) throws RepositoryException {
        Resource s = new URIImpl("http://localhost:8080/" + container.getPath());
        if (clear) {
            connection.clear(s);
        }
        for (Element element : container.getElements()) {
            URI p = new URIImpl(element.getAttribute());
            Value o = new LiteralImpl(element.getValue());
            Statement stmt = new StatementImpl(s, p, o);
            connection.add(stmt);
        }
    }

}