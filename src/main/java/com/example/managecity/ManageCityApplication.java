package com.example.managecity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class ManageCityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageCityApplication.class, args);
    }


}
