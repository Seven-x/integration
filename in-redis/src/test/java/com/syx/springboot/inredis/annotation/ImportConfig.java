package com.syx.springboot.inredis.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PersionOnImport.class})
public class ImportConfig {
    {
        System.out.println("ImportConfig");
    }
}
