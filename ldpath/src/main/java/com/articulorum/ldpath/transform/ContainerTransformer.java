package com.articulorum.ldpath.transform;

import java.io.IOException;

import javax.annotation.PostConstruct;

import com.articulorum.domain.Container;
import com.articulorum.ldpath.service.LdPathService;

import org.apache.marmotta.ldpath.exception.LDPathParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ContainerTransformer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private LdPathService ldPathService;

    @PostConstruct
    public void jmsSessionTransacted() {
        jmsTemplate.setSessionTransacted(true);
    }

    @JmsListener(destination = "topic.created")
    public void receiveCreated(@Payload Container container) throws LDPathParseException, IOException {
        jmsTemplate.convertAndSend("topic.index", ldPathService.programQuery(container.getPath()));
    }

    @JmsListener(destination = "topic.updated")
    public void receiveUpdated(@Payload Container container) throws LDPathParseException, IOException {
        jmsTemplate.convertAndSend("topic.reindex", ldPathService.programQuery(container.getPath()));
    }

    @JmsListener(destination = "topic.deleted")
    public void receiveDeleted(@Payload Container container) throws LDPathParseException, IOException {
        jmsTemplate.convertAndSend("topic.unindex", ldPathService.programQuery(container.getPath()));
    }

}