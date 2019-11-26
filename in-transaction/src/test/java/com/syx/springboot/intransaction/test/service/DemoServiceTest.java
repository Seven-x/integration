package com.syx.springboot.intransaction.test.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author shaoyx
 * @date 15:12  2019/11/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoServiceTest {

    @Autowired
    private DemoService demoService;

    @Test
    public void demoAdd() {
        demoService.demoAdd();
    }
}
