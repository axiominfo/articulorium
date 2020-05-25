package com.articulorum.api.producer;

import javax.annotation.PostConstruct;

import com.articulorum.domain.Container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ContainerProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostConstruct
    public void jmsSessionTransacted() {
        jmsTemplate.setSessionTransacted(true);
    }

    public void sendCreated(Container container) {
        System.out.println("created: " + container);
        jmsTemplate.convertAndSend("topic.created", container);
    }

    public void sendUpdated(Container container) {
        System.out.println("updated: " + container);
        jmsTemplate.convertAndSend("topic.updated", container);
    }

    public void sendDeleted(Container container) {
        System.out.println("deleted: " + container);
        jmsTemplate.convertAndSend("topic.deleted", container);
    }

}