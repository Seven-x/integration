package com.syx.springboot.inredis.test.aop.aspect;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author shaoyx
 * @date 14:42  2019/12/5
 */

@RunWith(SpringRunner.class)
@SpringBootTest
class AspectDemoTest {

    @Autowired
    private AspectDemo aspectDemo;

    @Test
    void aspect() {
        aspectDemo.aspect();
    }
}