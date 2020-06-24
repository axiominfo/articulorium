package com.articulorum.triplestore.config;

import com.articulorum.domain.Container;
import com.articulorum.event.RemoteEvent;
import com.articulorum.event.handler.RemoteEventHandler;
import com.articulorum.event.listener.RemoteEventListener;

import org.springframework.cloud.bus.ConditionalOnBusEnabled;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBusEnabled
@RemoteApplicationEventScan(basePackageClasses = { RemoteEvent.class })
public class BusConfiguration {

    @Bean
    public RemoteEventListener<Container> containerEventListener(RemoteEventHandler<Container> handler) {
        return new RemoteEventListener<Container>(handler);
    }

}