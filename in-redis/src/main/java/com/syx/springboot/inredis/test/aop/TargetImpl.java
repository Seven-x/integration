package com.syx.springboot.inredis.test.aop;

/**
 * @author syx
 * @date 15:21  2019/10/31
 */
public class TargetImpl implements Target {

    @Override
    public int test(int i) {
        System.out.println("test: "+i);
        return i + i;
    }
}
