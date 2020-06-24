package com.articulorum.event.listener;

import com.articulorum.event.RemoteEvent;
import com.articulorum.event.handler.RemoteEventHandler;

import org.springframework.context.ApplicationListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RemoteEventListener<P> implements ApplicationListener<RemoteEvent<P>> {

    private final RemoteEventHandler<P> handler;

    public RemoteEventListener(final RemoteEventHandler<P> handler) {
        this.handler = handler;
     }

    @Override
    public void onApplicationEvent(RemoteEvent<P> event) {
        log.info("EVENT {}, ORIGIN {}, DESTINATION {}", event.getAction(), event.getOriginService(), event.getDestinationService());
        handler.handle(event);
    }

}