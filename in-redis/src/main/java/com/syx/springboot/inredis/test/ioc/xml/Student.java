package com.syx.springboot.inredis.test.ioc.xml;

import lombok.Data;

/**
 * @author shaoyx
 * @date 9:58  2019/12/31
 */
@Data
public class Student extends Person {
    /**
     * 班级
     */
    private String className;

    /**
     * 学号
     */
    private String classNo;

}
