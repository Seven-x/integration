package com.syx.springboot.inredis.test.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author syx
 * @date 13:50  2019/10/11
 */
public class IocMain {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");

        ApplicationContext context1 = new AnnotationConfigApplicationContext();

        MessageService messageService = context.getBean(MessageService.class);
        messageService.getMessage();

        MessageService messageService1 = new MessageServiceImpl();
        messageService1.getMessage();

    }
}
