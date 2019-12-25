package com.syx.springboot.inredis.test.aop.aspect;

import org.springframework.stereotype.Component;

/**
 * @author shaoyx
 * @date 14:32  2019/12/5
 */
@Component
public class AspectDemo {

    public void aspect(){
        System.out.println("aop test!!!");
        throw new RuntimeException();
    }
}
