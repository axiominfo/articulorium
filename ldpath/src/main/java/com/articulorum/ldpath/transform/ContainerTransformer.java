package com.articulorum.ldpath.transform;

import java.io.IOException;

import javax.annotation.PostConstruct;

import com.articulorum.domain.Container;
import com.articulorum.ldpath.service.LdPathService;

import org.apache.marmotta.ldpath.exception.LDPathParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
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

    @JmsListener(destination = "created")
    public void receiveCreated(Container container) throws LDPathParseException, IOException {
        jmsTemplate.convertAndSend("index", ldPathService.programQuery(container.getPath()));
    }

    @JmsListener(destination = "updated")
    public void receiveUpdated(Container container) throws LDPathParseException, IOException {
        jmsTemplate.convertAndSend("reindex", ldPathService.programQuery(container.getPath()));
    }

    @JmsListener(destination = "deleted")
    public void receiveDeleted(Container container) throws LDPathParseException, IOException {
        jmsTemplate.convertAndSend("unindex", ldPathService.programQuery(container.getPath()));
    }

}