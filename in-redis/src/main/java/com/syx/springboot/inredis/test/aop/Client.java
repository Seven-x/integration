package com.syx.springboot.inredis.test.aop;

import com.syx.springboot.inredis.test.aop.cglib.CgLibDynamicProxy;
import com.syx.springboot.inredis.test.aop.jdk.JdkDynamicProxy;
import org.springframework.cglib.core.DebuggingClassWriter;

import java.util.Arrays;

/**
 * @author syx
 * @date 16:16  2019/10/31
 */
public class Client {

    public static void main(String[] args) {
        // 保存生产代理类的字节码文件
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\Projects\\integration");
        Target target = new TargetImpl();
//        testTargetJdkProxy(target);

        testTargetCgLibProxy(target);
    }

    private static void testTargetCgLibProxy(Target target) {
        Target targetCgLibProxy = CgLibDynamicProxy.newProxyInstance(target.getClass());
        targetCgLibProxy.hello("zeyusheng");
        System.out.println("targetCgLibProxy::- " + targetCgLibProxy.getClass());
        System.out.println("targetCgLibProxy::接口:- " + Arrays.toString(targetCgLibProxy.getClass().getInterfaces()));
        System.out.println("targetCgLibProxy::父类:- " + targetCgLibProxy.getClass().getSuperclass());
        System.out.println("targetCgLibProxy::父类::接口:- " + Arrays.toString(targetCgLibProxy.getClass().getSuperclass().getInterfaces()));
        System.out.println("targetCgLibProxy::父类::父类:- " + targetCgLibProxy.getClass().getSuperclass().getSuperclass());
    }

    private static void testTargetJdkProxy(Target target) {
        Target targetJdkProxy = new JdkDynamicProxy(target).newProxyInstance();
        targetJdkProxy.hello("shaoyx");
        System.out.println(Arrays.toString(targetJdkProxy.getClass().getConstructors()));
        System.out.println("targetJdkProxy:- " + targetJdkProxy.getClass());
        System.out.println("targetJdkProxy::接口:- " + Arrays.toString(targetJdkProxy.getClass().getInterfaces()));
        System.out.println("targetJdkProxy::父类:- " + targetJdkProxy.getClass().getSuperclass());
        System.out.println("targetJdkProxy::父类::接口:- " + Arrays.toString(targetJdkProxy.getClass().getSuperclass().getInterfaces()));
        System.out.println("targetJdkProxy::父类::父类:- " + targetJdkProxy.getClass().getSuperclass().getSuperclass());
    }
    // the object-oriented hierarchy


}