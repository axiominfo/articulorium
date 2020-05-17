package com.articulorum.web.producer;

import javax.annotation.PostConstruct;

import com.articulorum.domain.Container;
import com.articulorum.messaging.producer.EventProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ArtemisEventProducer implements EventProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostConstruct
    public void jmsSessionTransacted() {
        jmsTemplate.setSessionTransacted(true);
    }

    @Override
    public void sendCreated(Container container) {
        System.out.println("created: " + container);
        jmsTemplate.convertAndSend("created", container);
    }

    @Override
    public void sendUpdated(Container container) {
        System.out.println("updated: " + container);
        jmsTemplate.convertAndSend("updated", container);
    }

    @Override
    public void sendDeleted(Container container) {
        System.out.println("deleted: " + container);
        jmsTemplate.convertAndSend("deleted", container);
    }

}