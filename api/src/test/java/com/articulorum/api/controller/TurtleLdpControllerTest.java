package com.articulorum.api.controller;

import org.springframework.http.MediaType;

import com.articulorum.api.utility.RdfMediaType;

public class TurtleLdpControllerTest extends LdpControllerTest {

    public MediaType getAccept() {
        return RdfMediaType.TEXT_TURTLE;
    }

    public MediaType getContentType() {
        return RdfMediaType.TEXT_TURTLE;
    }

    public String getRdfType() {
        return "TURTLE";
    }

}
