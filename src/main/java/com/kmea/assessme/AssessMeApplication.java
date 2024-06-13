package com.kmea.assessme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.kmea.assessme")
public class AssessMeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssessMeApplication.class, args);
    }

}
