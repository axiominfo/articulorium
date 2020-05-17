package com.articulorum.ldpath.config;

import static java.lang.String.join;
import static org.apache.commons.lang3.StringUtils.removeEnd;

import java.io.File;
import java.io.IOException;

import org.apache.marmotta.commons.http.ContentType;
import org.apache.marmotta.ldcache.api.LDCachingBackend;
import org.apache.marmotta.ldcache.backend.file.LDCachingFileBackend;
import org.apache.marmotta.ldcache.model.CacheConfiguration;
import org.apache.marmotta.ldcache.services.LDCache;
import org.apache.marmotta.ldclient.api.endpoint.Endpoint;
import org.apache.marmotta.ldclient.endpoint.rdf.LinkedDataEndpoint;
import org.apache.marmotta.ldclient.model.ClientConfiguration;
import org.apache.marmotta.ldclient.provider.rdf.CacheProvider;
import org.apache.marmotta.ldclient.provider.rdf.LinkedDataProvider;
import org.apache.marmotta.ldclient.provider.rdf.RegexUriProvider;
import org.apache.marmotta.ldclient.provider.rdf.SPARQLProvider;
import org.apache.marmotta.ldpath.backend.linkeddata.LDCacheBackend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LdPathConfig {

    @Value("${ldpath.namespace:Articulorum Web API}")
    private String name;

    @Value("${ldpath.namespace:http://localhost:8080}")
    private String namespace;

    @Value("${ldpath.defaultExpiry:6000}")
    private Long defaultExpiry;

    @Value("${ldpath.cache.location:cache}")
    private String cacheLocation;

    @Bean
    public LDCachingBackend cachingBackend() throws IOException {
        LDCachingBackend backend = new LDCachingFileBackend(new File(cacheLocation));
        backend.initialize();
        return backend;
    }

    @Bean
    public Endpoint endpoint() {
        String uriPattern = join("/", removeEnd(namespace, "/"), ".*", "/");
        String type = LinkedDataProvider.PROVIDER_NAME;
        Endpoint endpoint = new Endpoint(name, type, uriPattern, null, defaultExpiry);
        endpoint.setPriority(Endpoint.PRIORITY_HIGH);
        endpoint.addContentType(new ContentType("text", "turtle", 1.0));
        endpoint.addContentType(new ContentType("text", "n3", 0.8));
        endpoint.addContentType(new ContentType("application", "rdf+xml", 0.8));
        endpoint.addContentType(new ContentType("application", "ld+json", 0.5));
        return endpoint;
    }

    @Bean
    public ClientConfiguration clientConfiguration() {
        final ClientConfiguration client = new ClientConfiguration();

        client.addProvider(new LinkedDataProvider());
        client.addProvider(new CacheProvider());
        client.addProvider(new RegexUriProvider());
        client.addProvider(new SPARQLProvider());

        client.addEndpoint(new LinkedDataEndpoint());
        client.addEndpoint(endpoint());

        return client;
    }

    @Bean
    public CacheConfiguration cacheConfiguration() {
        CacheConfiguration configuration = new CacheConfiguration();
        configuration.setClientConfiguration(clientConfiguration());
        configuration.setDefaultExpiry(defaultExpiry);
        return configuration;
    }

    @Bean
    public LDCache cache() throws IOException {
        LDCache cache = new LDCache(cacheConfiguration(), cachingBackend());
        return cache;
    }


    @Bean
    public LDCacheBackend backend() throws IOException {
        LDCacheBackend backend = new LDCacheBackend(cache());
        return backend;
    }
}