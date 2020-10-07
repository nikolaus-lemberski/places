package com.lemberski.demo.places;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
@EnableDiscoveryClient
@EnableCircuitBreaker
public class PlacesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlacesApplication.class, args);
    }

}
