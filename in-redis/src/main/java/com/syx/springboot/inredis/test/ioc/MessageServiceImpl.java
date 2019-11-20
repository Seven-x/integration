package com.syx.springboot.inredis.test.ioc;

/**
 * @author syx
 * @date 13:49  2019/10/11
 */
public class MessageServiceImpl implements MessageService {

    @Override
    public void getMessage() {
        System.out.println("Hello World");
    }
}
