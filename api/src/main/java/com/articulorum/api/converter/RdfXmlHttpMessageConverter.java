package com.articulorum.api.converter;

import com.articulorum.api.utility.RdfMediaType;

public class RdfXmlHttpMessageConverter extends AbstractRdfHttpMessageConverter {

    public RdfXmlHttpMessageConverter() {
        super(RdfMediaType.APPLICATION_RDF_XML);
    }

    public String getRdfType() {
        return "RDF/XML";
    }

}