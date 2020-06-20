package com.articulorum.ldp.controller;

import org.springframework.http.MediaType;

import com.articulorum.ldp.utility.RdfMediaType;

public class JsonLdLdpControllerTest extends LdpControllerTest {

    public MediaType getAccept() {
        return RdfMediaType.APPLICATION_JSON_LD;
    }

    public MediaType getContentType() {
        return RdfMediaType.APPLICATION_JSON_LD;
    }

    public String getRdfType() {
        return "JSON-LD";
    }

}