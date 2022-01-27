package com.example.cryptotestproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CryptoTestProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoTestProjectApplication.class, args);
    }

}
