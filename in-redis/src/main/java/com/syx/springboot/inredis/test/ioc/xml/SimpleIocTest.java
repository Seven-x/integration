package com.syx.springboot.inredis.test.ioc.xml;

import java.util.Objects;

/**
 * @author shaoyx
 * @date 9:54  2019/12/31
 */
public class SimpleIocTest {

    public static void main(String[] args) throws Exception {
        String location = Objects.requireNonNull(SimpleIoc.class.getClassLoader().getResource("simple-ioc.xml")).getFile();
        SimpleIoc ioc = new SimpleIoc(location);
        Person person = (Person) ioc.getBean("person");
        System.out.println(person);
        Student student = (Student) ioc.getBean("student");
        System.out.println(student);
    }
}
