package com.articulorum.web.converter;

import com.articulorum.web.utility.RdfMediaType;

public class RdfJsonHttpMessageConverter extends AbstractRdfHttpMessageConverter {

    public RdfJsonHttpMessageConverter() {
        super(RdfMediaType.APPLICATION_RDF_JSON);
    }

    public String getRdfType() {
        return "RDF/JSON";
    }

}