package com.articulorum.solr.factory;

import com.articulorum.solr.config.model.SolrConfig;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SolrClientFactory {

    @Autowired
    private SolrConfig solrConfig;

    public SolrClient getCloudSolrClient() {
        return new Builder(solrConfig.getHosts())
            .withConnectionTimeout(10000)
            .withSocketTimeout(60000)
            .build();
    }

}