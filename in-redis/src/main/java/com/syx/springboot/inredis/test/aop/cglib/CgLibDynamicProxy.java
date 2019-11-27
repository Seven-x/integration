package com.syx.springboot.inredis.test.aop.cglib;

import com.syx.springboot.inredis.test.aop.Target;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.invoke.MethodHandleInfo;
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

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        return proxy.invokeSuper(obj, args);
    }
}
