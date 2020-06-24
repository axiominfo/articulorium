package com.articulorum.index.config;

import java.util.Collection;
import java.util.Map;

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
    public RemoteEventListener<Map<String, Collection<?>>> containerEventListener(RemoteEventHandler<Map<String, Collection<?>>> handler) {
        return new RemoteEventListener<Map<String, Collection<?>>>(handler);
    }

}