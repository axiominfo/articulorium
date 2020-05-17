package com.articulorum.web.service;

import java.util.Optional;

import com.articulorum.domain.Container;

public interface ContainerService {

    boolean existsByPath(String path);

    Optional<Container> findByPath(String path);

    String create(Container parent, Container container, String uri);

    void delete(Container parent, Container container, String uri);

}