package com.articulorum.index.consumer;

import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class DocumentConsumer {

    @Autowired
    private SolrClient solrClient;

    @JmsListener(destination = "topic.index")
    public void receiveCreated(@Payload Map<String, Object> document) {
        System.out.println("index: " + document);
        try {
            solrClient.add("test", toSolrInputDocument(document));
            solrClient.commit("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JmsListener(destination = "topic.reindex")
    public void receiveUpdated(@Payload Map<String, Object> document) {
        System.out.println("reindex: " + document);
        try {
            solrClient.add("test", toSolrInputDocument(document));
            solrClient.commit("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JmsListener(destination = "topic.unindex")
    public void receiveDeleted(@Payload Map<String, Object> document) {
        System.out.println("unindex: " + document);
        String id = (String) document.get("id");
        try {
            solrClient.deleteById("test", id);
            solrClient.commit("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SolrInputDocument toSolrInputDocument(Map<String, Object> document) {
        SolrInputDocument solrInDoc = new SolrInputDocument();
        for (Map.Entry<String, Object> entry : document.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            solrInDoc.addField(key, value);
        }
        return solrInDoc;
    }
}