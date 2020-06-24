package com.articulorum.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

import lombok.Getter;
import lombok.Setter;

public class RemoteEvent<P> extends RemoteApplicationEvent {

    private static final long serialVersionUID = -8887607843876865L;

    @Getter
    @Setter
    private P payload;

    @Getter
    @Setter
    private RemoteEventAction action;

    public RemoteEvent() { }

    public RemoteEvent(Object source, String originService, P payload, RemoteEventAction action) {
        super(source, originService);
        this.payload = payload;
        this.action = action;
    }

}