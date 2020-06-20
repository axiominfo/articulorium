package com.articulorum.ldp.controller;

import org.springframework.http.MediaType;

import com.articulorum.ldp.utility.RdfMediaType;

public class N3LdpControllerTest extends LdpControllerTest {

    public MediaType getAccept() {
        return RdfMediaType.TEXT_N3;
    }

    public MediaType getContentType() {
        return RdfMediaType.TEXT_N3;
    }

    public String getRdfType() {
        return "N3";
    }

}
