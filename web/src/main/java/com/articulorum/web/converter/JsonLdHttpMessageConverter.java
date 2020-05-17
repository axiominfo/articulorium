package com.articulorum.web.converter;

import com.articulorum.web.utility.RdfMediaType;

public class JsonLdHttpMessageConverter extends AbstractRdfHttpMessageConverter {

    public JsonLdHttpMessageConverter() {
        super(RdfMediaType.APPLICATION_JSON_LD);
    }

    public String getRdfType() {
        return "JSON-LD";
    }

}