package com.syx.springboot.ineureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author shaoyx
 */
@EnableEurekaServer
@SpringBootApplication
public class InEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(InEurekaApplication.class, args);
    }

}
