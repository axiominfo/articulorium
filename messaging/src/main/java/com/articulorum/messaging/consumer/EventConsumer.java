package com.articulorum.messaging.consumer;

import com.articulorum.domain.Container;

public interface EventConsumer {

    public void receiveCreated(Container container);

    public void receiveUpdated(Container container);

    public void receiveDeleted(Container container);

}