package com.articulorum.index.config;

import com.articulorum.index.service.SolrCollectionService;

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
            if (solrCollectionService.hasCollection("test")) {
                return;
            }
            solrCollectionService.createCollection("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}