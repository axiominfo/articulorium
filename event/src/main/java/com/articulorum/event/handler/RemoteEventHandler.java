package com.articulorum.event.handler;

import com.articulorum.event.RemoteEvent;

public interface RemoteEventHandler<P> {

    void handle(RemoteEvent<P> event);
    
}