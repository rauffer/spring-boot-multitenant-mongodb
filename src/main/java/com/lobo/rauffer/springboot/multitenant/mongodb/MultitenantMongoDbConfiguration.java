package com.lobo.rauffer.springboot.multitenant.mongodb;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

/**
 * Configuration class to expose @{link MultitenantMongoDbFactory} as a Spring bean.
 * 
 * @author Rauffer Lobo
 */
@Configuration
@ConditionalOnProperty(name="spring.boot.multitenant.mongodb.enabled", havingValue="true", matchIfMissing=true)
public class MultitenantMongoDbConfiguration {

    @Autowired(required = false)
    private MongoClientOptions options;

    @Autowired
    private Environment environment;

    @Autowired
    private MongoProperties properties;

    @Bean
    public MongoClient createMongoClient() throws UnknownHostException {
        return properties.createMongoClient(options, environment);
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        return new MultitenantMongoDbFactory(createMongoClient(), properties.getDatabase());
    }

}
