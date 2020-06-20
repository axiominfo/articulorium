package com.articulorum.ldp.converter;

import com.articulorum.ldp.utility.RdfMediaType;

public class NTriplesHttpMessageConverter extends AbstractRdfHttpMessageConverter {

    public NTriplesHttpMessageConverter() {
        super(RdfMediaType.APPLICATION_N_TRIPLES);
    }

    public String getRdfType() {
        return "N-TRIPLES";
    }

}