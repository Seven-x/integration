package com.syx.springboot.inredis.test.aop;

import com.syx.springboot.inredis.test.aop.jdk.JdkDynamicProxy;

/**
 * @author syx
 * @date 16:16  2019/10/31
 */
public class Client {

    public static void main(String[] args) {
        // 保存生产代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Target targetJdkProxy = new JdkDynamicProxy(new TargetImpl()).newProxyInstance();
        System.out.println(targetJdkProxy.test(1));
    }
}
