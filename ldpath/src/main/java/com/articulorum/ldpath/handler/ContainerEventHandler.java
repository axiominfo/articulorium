package com.articulorum.ldpath.handler;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import com.articulorum.domain.Container;
import com.articulorum.event.RemoteEvent;
import com.articulorum.event.handler.RemoteEventHandler;
import com.articulorum.ldpath.service.LdPathService;

import org.apache.marmotta.ldpath.exception.LDPathParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ContainerEventHandler implements RemoteEventHandler<Container> {

    @Autowired
    private LdPathService ldPathService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private BusProperties busProperties;

    @Override
    public void handle(RemoteEvent<Container> event) {
        Container container = event.getPayload();
        final String originService = busProperties.getId();
        try {
            Map<String, Collection<?>> document = ldPathService.programQuery(container.getPath());
            log.info("EMIT {}, DOCUMENT {}, ORIGIN {}", event.getAction(), container.getId(), originService);
            publisher.publishEvent(new RemoteEvent<Map<String, Collection<?>>>(this, originService, document, event.getAction()));
        } catch (LDPathParseException | IOException e) {
            log.warn("Failed to process ldpath for container {}" , container.getId());
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
    }

}