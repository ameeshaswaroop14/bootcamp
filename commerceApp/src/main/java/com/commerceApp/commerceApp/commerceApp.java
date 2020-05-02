package com.commerceApp.commerceApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;



@SpringBootApplication
@EnableAsync

public class commerceApp {

    public static void main(String[] args) {
        SpringApplication.run(commerceApp.class, args);
    }

}
