package com.articulorum.web.converter;

import com.articulorum.web.utility.RdfMediaType;

public class NTriplesHttpMessageConverter extends AbstractRdfHttpMessageConverter {

    public NTriplesHttpMessageConverter() {
        super(RdfMediaType.APPLICATION_N_TRIPLES);
    }

    public String getRdfType() {
        return "N-TRIPLES";
    }

}