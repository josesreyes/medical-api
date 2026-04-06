package com.jsrdev.medapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class MedApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedApiApplication.class, args);

        for (int i = 0; i < 5; i++) {
            System.out.println(UUID.randomUUID());
        }
    }

}
