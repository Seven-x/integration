package com.syx.springboot.inredis.test.ioc.xml;

import lombok.Data;

/**
 * @author shaoyx
 * @date 9:54  2019/12/31
 */
@Data
public class Person {

    /**
     * 姓名
     */
    protected String name;

    /**
     * 姓名 1-男 2-女
     */
    protected String sex;

    /**
     * 年龄
     */
    protected String age;
}
