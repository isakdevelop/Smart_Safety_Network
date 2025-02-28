//package com.smartsafetynetwork.api.common.config;
//
//import org.elasticsearch.client.RestClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchConfiguration;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.smartsafetynetwork.api.elasticSearch.repository")
//public class ElasticsearchConfig {
//
//    @Bean
//    public RestClient restClient client() {
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("localhost:9200") // Elasticsearch 서버 주소
//                .build();
//
//        return RestClients.create(clientConfiguration).rest();
//    }
//
//    @Bean
//    public ElasticsearchRestTemplate elasticsearchTemplate() {
//        return new ElasticsearchRestTemplate(client());
//    }
//}