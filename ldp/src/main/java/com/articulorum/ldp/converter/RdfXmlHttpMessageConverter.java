package com.articulorum.ldp.converter;

import com.articulorum.ldp.utility.RdfMediaType;

public class RdfXmlHttpMessageConverter extends AbstractRdfHttpMessageConverter {

    public RdfXmlHttpMessageConverter() {
        super(RdfMediaType.APPLICATION_RDF_XML);
    }

    public String getRdfType() {
        return "RDF/XML";
    }

}