package com.articulorum.index;

import com.articulorum.index.config.model.SolrConfig;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.articulorum")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public SolrClient getCloudSolrClient(SolrConfig solrConfig) {
        return new CloudSolrClient.Builder(solrConfig.getZkHosts(), solrConfig.getZkChroot())
            .withConnectionTimeout(solrConfig.getConnectionTimeout())
            .withSocketTimeout(solrConfig.getSocketTimeout())
            .build();
    }

}
