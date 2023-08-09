package com.tinqin.bff.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages ="com.tinqin.bff" )
@ComponentScan(basePackages = "com.tinqin.bff")
@EnableJpaRepositories(basePackages = {"com.tinqin.bff.persistence.repositories"})
@EntityScan(basePackages = {"com.tinqin.bff.persistence.entities"})
public class BffApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffApplication.class, args);
    }

}
