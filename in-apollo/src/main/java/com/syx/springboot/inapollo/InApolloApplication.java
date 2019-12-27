package com.syx.springboot.inapollo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class InApolloApplication {

    public static void main(String[] args) {
        SpringApplication.run(InApolloApplication.class, args);
    }

}
