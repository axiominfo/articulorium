package com.articulorum.triplestore.consumer;

import com.articulorum.domain.Container;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ContainerConsumer {

    @JmsListener(destination = "topic.created")
    public void receiveCreated(@Payload Container container) {
        System.out.println("created: " + container);
    }

    @JmsListener(destination = "topic.updated")
    public void receiveUpdated(@Payload Container container) {
        System.out.println("updated: " + container);
    }

    @JmsListener(destination = "topic.deleted")
    public void receiveDeleted(@Payload Container container) {
        System.out.println("deleted: " + container);
    }

}