package com.lobo.rauffer.springboot.multitenant.mongodb;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration
public class MultitenantMongoDbApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(MultitenantMongoDbApplicationTest.class, args);
    }
    
    @Bean
    public HttpServletRequest httpServletRequest() {
        return mock(HttpServletRequest.class);
    }
    
}