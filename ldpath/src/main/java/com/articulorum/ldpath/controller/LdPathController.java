package com.articulorum.ldpath.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import com.articulorum.ldpath.service.LdPathService;

import org.apache.marmotta.ldpath.exception.LDPathParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LdPathController {

    @Autowired
    private LdPathService ldpathService;

    @GetMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Map<String, Collection<?>> get(@RequestParam(required = true) String uri) throws LDPathParseException, IOException {
        return ldpathService.programQuery(uri);
    }

    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Map<String, Collection<?>> get(@RequestParam(required = true) String uri, InputStream program) throws LDPathParseException, IOException {
        return ldpathService.programQuery(uri, program);
    }

}