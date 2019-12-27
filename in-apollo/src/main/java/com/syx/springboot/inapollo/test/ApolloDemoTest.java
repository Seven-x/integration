package com.syx.springboot.inapollo.test;

import com.syx.springboot.inapollo.config.ApolloEntityDemo;

/**
 * @author shaoyx
 * @date 15:40  2019/12/26
 */
public class ApolloDemoTest {

    public static void main(String[] args) {
        ApolloEntityDemo apolloEntityDemo = new ApolloEntityDemo();
        System.out.println(apolloEntityDemo.getPersonId());
        System.out.println(apolloEntityDemo.getPersonName());
    }
}
