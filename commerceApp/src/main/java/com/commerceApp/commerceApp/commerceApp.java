package com.commerceApp.commerceApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
@EnableAsync
@EnableCaching
public class commerceApp {
    public static final Logger logger = LoggerFactory.getLogger(commerceApp.class);


    public static void main(String[] args) {
        SpringApplication.run(commerceApp.class, args);
    }


}
