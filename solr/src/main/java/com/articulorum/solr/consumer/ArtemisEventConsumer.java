package com.articulorum.solr.consumer;

import com.articulorum.domain.Container;
import com.articulorum.messaging.consumer.EventConsumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ArtemisEventConsumer implements EventConsumer {

    @Override
    @JmsListener(destination = "created")
    public void receiveCreated(Container container) {
        System.out.println("created: " + container);
    }

    @Override
    @JmsListener(destination = "updated")
    public void receiveUpdated(Container container) {
        System.out.println("updated: " + container);
    }

    @Override
    @JmsListener(destination = "deleted")
    public void receiveDeleted(Container container) {
        System.out.println("deleted: " + container);
    }

}