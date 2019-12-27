package com.syx.springboot.inapollo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author shaoyx
 * @date 15:35  2019/12/26
 */
@Data
public class ApolloEntityDemo {

    @Value("$(person.name:哇啊啊)")
    private String personName;

    @Value("$(person.id:1)")
    private String personId;
}
