package com.prep.spring_boot_v1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.prep.spring_boot_v1.repository")
public class MongoConfig {}
