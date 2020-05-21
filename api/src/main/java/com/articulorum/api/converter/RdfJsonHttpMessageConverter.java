package com.articulorum.api.converter;

import com.articulorum.api.utility.RdfMediaType;

public class RdfJsonHttpMessageConverter extends AbstractRdfHttpMessageConverter {

    public RdfJsonHttpMessageConverter() {
        super(RdfMediaType.APPLICATION_RDF_JSON);
    }

    public String getRdfType() {
        return "RDF/JSON";
    }

}