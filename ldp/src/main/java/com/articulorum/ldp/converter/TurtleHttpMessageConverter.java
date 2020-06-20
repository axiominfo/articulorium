package com.articulorum.ldp.converter;

import java.util.Arrays;

import com.articulorum.ldp.utility.RdfMediaType;

public class TurtleHttpMessageConverter extends AbstractRdfHttpMessageConverter {

    public TurtleHttpMessageConverter() {
        super(RdfMediaType.TEXT_TURTLE);
        setSupportedMediaTypes(Arrays.asList(RdfMediaType.TEXT_TURTLE, RdfMediaType.APPLICATION_X_TURTLE));
    }

    public String getRdfType() {
        return "TURTLE";
    }

}