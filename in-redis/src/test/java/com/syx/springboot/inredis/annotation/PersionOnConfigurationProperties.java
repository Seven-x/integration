package com.syx.springboot.inredis.annotation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author EDZ
 */
@Component
@Data
@ConfigurationProperties(prefix = "persion")
public class PersionOnConfigurationProperties {

    private Integer userId;

    private String userName;

}
