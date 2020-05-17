package com.articulorum.web.controller;

import org.springframework.http.MediaType;

import com.articulorum.web.utility.RdfMediaType;

public class RdfJsonLdpControllerTest extends LdpControllerTest {

    public MediaType getAccept() {
        return RdfMediaType.APPLICATION_RDF_JSON;
    }

    public MediaType getContentType() {
        return RdfMediaType.APPLICATION_RDF_JSON;
    }

    public String getRdfType() {
        return "RDF/JSON";
    }

}
