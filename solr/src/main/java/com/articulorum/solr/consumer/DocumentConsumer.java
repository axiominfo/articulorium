package com.articulorum.solr.consumer;

import java.util.Collection;
import java.util.Map;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class DocumentConsumer {

    @JmsListener(destination = "index")
    public void receiveCreated(Map<String, Collection<?>> document) {
        System.out.println("index: " + document);
    }

    @JmsListener(destination = "reindex")
    public void receiveUpdated(Map<String, Collection<?>> document) {
        System.out.println("reindex: " + document);
    }

    @JmsListener(destination = "unindex")
    public void receiveDeleted(Map<String, Collection<?>> document) {
        System.out.println("unindex: " + document);
    }

}