package com.syx.springboot.inredis.test.aop;

import com.syx.springboot.inredis.test.aop.cglib.CgLibDynamicProxy;
import com.syx.springboot.inredis.test.aop.jdk.JdkDynamicProxy;

/**
 * @author syx
 * @date 16:16  2019/10/31
 */
public class Client {

    public static void main(String[] args) {
        // 保存生产代理类的字节码文件
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Target target = new TargetImpl();
        Target targetJdkProxy = new JdkDynamicProxy(target).newProxyInstance();
        targetJdkProxy.hello("shaoyx");

        Target targetCgLibProxy = CgLibDynamicProxy.newProxyInstance(target.getClass());
        targetCgLibProxy.hello("zeyusheng");
    }
}
