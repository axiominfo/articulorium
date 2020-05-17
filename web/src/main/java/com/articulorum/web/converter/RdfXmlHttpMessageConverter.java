package com.articulorum.web.converter;

import com.articulorum.web.utility.RdfMediaType;

public class RdfXmlHttpMessageConverter extends AbstractRdfHttpMessageConverter {

    public RdfXmlHttpMessageConverter() {
        super(RdfMediaType.APPLICATION_RDF_XML);
    }

    public String getRdfType() {
        return "RDF/XML";
    }

}