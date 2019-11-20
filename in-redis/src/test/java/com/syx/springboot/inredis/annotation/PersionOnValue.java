package com.syx.springboot.inredis.annotation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author EDZ
 */
@Component
@Data
public class PersionOnValue {

    @Value("${persion.userId}")
    private Integer userId;

    @Value("${persion.userName}")
    private String userName;

}
