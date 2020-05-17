package com.articulorum.web.converter;

import java.util.Arrays;

import com.articulorum.web.utility.RdfMediaType;

public class N3HttpMessageConverter extends AbstractRdfHttpMessageConverter {

    public N3HttpMessageConverter() {
        super(RdfMediaType.TEXT_N3);
        setSupportedMediaTypes(Arrays.asList(RdfMediaType.TEXT_N3, RdfMediaType.TEXT_RDF_N3));
    }

    public String getRdfType() {
        return "N3";
    }

}