package com.articulorum.messaging.producer;

import com.articulorum.domain.Container;

public interface EventProducer {

    void sendCreated(Container container);

    void sendUpdated(Container container);

    void sendDeleted(Container container);

}