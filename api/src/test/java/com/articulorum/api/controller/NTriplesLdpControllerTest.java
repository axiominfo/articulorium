package com.articulorum.api.controller;

import org.springframework.http.MediaType;

import com.articulorum.api.utility.RdfMediaType;

public class NTriplesLdpControllerTest extends LdpControllerTest {

    public MediaType getAccept() {
        return RdfMediaType.APPLICATION_N_TRIPLES;
    }

    public MediaType getContentType() {
        return RdfMediaType.APPLICATION_N_TRIPLES;
    }

    public String getRdfType() {
        return "N-TRIPLES";
    }

}
