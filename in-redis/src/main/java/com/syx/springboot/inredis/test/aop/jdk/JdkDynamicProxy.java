package com.syx.springboot.inredis.test.aop.jdk;

import com.syx.springboot.inredis.test.aop.Target;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * 1. 通过实现InvocationHandler接口 创建自己的调用处理器
 * 2. 通过代理类Proxy指定ClassLoader对象和一组interface来创建动态代理类
 * 3. 通过反射机制获取动态代理类的构造函数，其唯一参数类型就是调用处理器接口类型
 * 4. 通过构造函数创建动态代理类实例，构造时调用处理器对象作为参数传入
 *
 * @author syx
 * @date 15:02  2019/10/31
 */
public class JdkDynamicProxy implements InvocationHandler {

    private Object target;

    public JdkDynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * 2. 通过代理类Proxy指定ClassLoader对象和一组interface来创建动态代理类
     */
    @SuppressWarnings("unchecked")
    public <T> T newProxyInstance() {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    /**
     * 1. 通过实现InvocationHandler接口 创建自己的调用处理器
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Do something before!");
        Object object = method.invoke(target, args);
        System.out.println("Do something after!");
        return object;
    }
}
