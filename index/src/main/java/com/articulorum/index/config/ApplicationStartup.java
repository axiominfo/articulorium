package com.articulorum.index.config;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.client.solrj.request.CollectionAdminRequest.Create;
import org.apache.solr.client.solrj.response.CollectionAdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private SolrClient solrClient;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        try {
            if (hasCollection("test")) {
                return;
            }
            createCollection("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private java.util.List<String> listCollections() throws IOException, SolrServerException {
        return CollectionAdminRequest.listCollections(solrClient);
    }

    private boolean hasCollection(String name) throws IOException, SolrServerException {
        return listCollections().contains(name);
    }

    private CollectionAdminResponse createCollection(String name) throws SolrServerException, IOException {
        Create create = CollectionAdminRequest.createCollection(name, 1, 1);
        return create.process(solrClient);
    }

}