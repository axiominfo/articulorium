package com.articulorum.triplestore.consumer;

import com.articulorum.domain.Container;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ContainerConsumer {

    @JmsListener(destination = "created")
    public void receiveCreated(Container container) {
        System.out.println("created: " + container);
    }

    @JmsListener(destination = "updated")
    public void receiveUpdated(Container container) {
        System.out.println("updated: " + container);
    }

    @JmsListener(destination = "deleted")
    public void receiveDeleted(Container container) {
        System.out.println("deleted: " + container);
    }

}