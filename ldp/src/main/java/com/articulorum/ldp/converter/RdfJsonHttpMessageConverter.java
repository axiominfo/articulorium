package com.articulorum.ldp.converter;

import com.articulorum.ldp.utility.RdfMediaType;

public class RdfJsonHttpMessageConverter extends AbstractRdfHttpMessageConverter {

    public RdfJsonHttpMessageConverter() {
        super(RdfMediaType.APPLICATION_RDF_JSON);
    }

    public String getRdfType() {
        return "RDF/JSON";
    }

}