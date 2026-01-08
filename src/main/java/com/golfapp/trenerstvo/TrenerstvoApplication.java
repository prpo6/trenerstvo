package com.golfapp.trenerstvo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.golfapp.trenerstvo.model")
public class TrenerstvoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrenerstvoApplication.class, args);
    }
}