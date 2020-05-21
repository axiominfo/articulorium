package com.articulorum.api.converter;

import com.articulorum.api.utility.RdfMediaType;

public class NTriplesHttpMessageConverter extends AbstractRdfHttpMessageConverter {

    public NTriplesHttpMessageConverter() {
        super(RdfMediaType.APPLICATION_N_TRIPLES);
    }

    public String getRdfType() {
        return "N-TRIPLES";
    }

}