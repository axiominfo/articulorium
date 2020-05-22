package com.articulorum.solr.consumer;

import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class DocumentConsumer {

    @Autowired
    private SolrClient solrClient;

    @JmsListener(destination = "index")
    public void receiveCreated(Map<String, Object> document) {
        System.out.println("index: " + document);

        SolrInputDocument solrInDoc = new SolrInputDocument();
        for (Map.Entry<String, Object> entry : document.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            solrInDoc.addField(key, value);
        }
        try {
            solrClient.add("test", solrInDoc);
            solrClient.commit("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JmsListener(destination = "reindex")
    public void receiveUpdated(Map<String, Object> document) {
        System.out.println("reindex: " + document);
    }

    @JmsListener(destination = "unindex")
    public void receiveDeleted(Map<String, Object> document) {
        System.out.println("unindex: " + document);
    }

}