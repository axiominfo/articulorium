package com.articulorum.solr.config;

import java.io.IOException;

import com.articulorum.solr.service.SolrCollectionService;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.CollectionAdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private SolrCollectionService solrCollectionService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        try {
            CollectionAdminResponse response = solrCollectionService.createCollection("test");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }

}