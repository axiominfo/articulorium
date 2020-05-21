package com.articulorum.solr.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.client.solrj.request.CollectionAdminRequest.Create;
import org.apache.solr.client.solrj.response.CollectionAdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolrCollectionService {

    @Autowired
    private SolrClient solrClient;

    public List<String> listCollections() throws IOException, SolrServerException {
        return CollectionAdminRequest.listCollections(solrClient);
    }

    public boolean hasCollection(String name) throws IOException, SolrServerException {
        return listCollections().contains(name);
    }

    public CollectionAdminResponse createCollection(String name) throws SolrServerException, IOException {
        Create create = CollectionAdminRequest.createCollection(name, 3, 3);
        return create.process(solrClient);
    }

}