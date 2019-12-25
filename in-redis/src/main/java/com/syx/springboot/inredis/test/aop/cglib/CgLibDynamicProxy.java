package com.syx.springboot.inredis.test.aop.cglib;

import com.syx.springboot.inredis.test.aop.Target;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author shaoyx
 * @date 17:22  2019/11/27
 */
public class CgLibDynamicProxy implements MethodInterceptor {

    public CgLibDynamicProxy() {
    }

    public static <T extends Target> Target newProxyInstance(Class<T> instanceClass){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(instanceClass);
        enhancer.setCallback(new CgLibDynamicProxy());
        return (Target) enhancer.create();
    }

    /**
     *
     * @author shaoyx
     * @param obj 代理对象
     * @param method 被代理对象方法
     * @param args 方法入参
     * @param proxy 代理方法
     * @return
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Do something before!");
        Object object = proxy.invokeSuper(obj, args);
        System.out.println("Do something after!");
        return object;
    }
}
