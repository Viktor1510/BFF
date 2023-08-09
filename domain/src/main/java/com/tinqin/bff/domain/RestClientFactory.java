package com.tinqin.bff.domain;


import com.example.zoostore.ZooStoreRestExport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoostore.storage.restexport.StorageRestExport;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestClientFactory {

    @Bean
    ZooStoreRestExport getZooStoreExportClient(){
        final ObjectMapper objectMapper=new ObjectMapper();
        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(ZooStoreRestExport.class,"http://localhost:8080");
    }

    @Bean
    StorageRestExport getStorageExportClient(){
        final  ObjectMapper objectMapper=new ObjectMapper();
        return  Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(StorageRestExport.class,"http://localhost:8081");

    }
}
