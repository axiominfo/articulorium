package com.articulorum.web.controller;

import org.springframework.http.MediaType;

import com.articulorum.web.utility.RdfMediaType;

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