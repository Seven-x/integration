package com.syx.springboot.inapollo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author shaoyx
 * @date 13:56  2019/12/27
 */
@Data
@Configuration
public class AppConfig {

    @Value("${person.name:哇啊啊}")
    private String personName;

    @Value("${person.id:1}")
    private String personId;
}
