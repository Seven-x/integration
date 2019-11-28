package com.syx.springboot.inredis.test.aop.cglib;

import com.syx.springboot.inredis.test.aop.Target;
import com.syx.springboot.inredis.test.aop.TargetBean;
import com.syx.springboot.inredis.test.aop.TargetBean1;
import com.syx.springboot.inredis.test.aop.TargetImpl;
import org.junit.Test;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.ImmutableBean;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @author shaoyx
 * @date 10:17  2019/11/28
 */
public class CgLibDynamicProxyTest {

    @Test
    public void testFixedValue(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "hello cglib";
            }
        });
        Target targetProxy = (Target) enhancer.create();
        System.out.println(targetProxy.hello("shaoyx"));
        System.out.println(targetProxy.toString());
        System.out.println(targetProxy.getClass());
        System.out.println(targetProxy.hashCode());
    }

    @Test
    public void testInvocationHandler(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class){
                    return "hello cglib";
                }
                else {
                    throw new RuntimeException("Do not know what to do");
                }
            }
        });
        Target targetProxy = (Target) enhancer.create();
        System.out.println(targetProxy.hello("shaoyx"));
        System.out.println(targetProxy.toString());
    }

    @Test
    public void testMethodInterceptor(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class){
                    return "hello cglib";
                }
                else {
                    throw new RuntimeException("Do not know what to do");
                }
            }
        });
        Target targetProxy = (Target) enhancer.create();
        System.out.println(targetProxy.hello("shaoyx"));
        System.out.println(targetProxy.toString());
    }

    @Test
    public void testCallBackFilter(){
        CallbackHelper callbackHelper = new CallbackHelper(TargetImpl.class, new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class){
                    return new FixedValue() {
                        @Override
                        public Object loadObject() throws Exception {
                            return "hello cglib";
                        }
                    };
                }else {
                    return NoOp.INSTANCE;
                }
            }
        };
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetImpl.class);
        enhancer.setCallbackFilter(callbackHelper);
        enhancer.setCallbacks(callbackHelper.getCallbacks());
        Target targetProxy = (Target) enhancer.create();
        System.out.println(targetProxy.hello("shaoyx"));
        System.out.println(targetProxy.toString());
        System.out.println(targetProxy.hashCode());
    }

    @Test
    public void testImmutableBean(){
        TargetBean bean = new TargetBean();
        bean.setValue("hello world");
        TargetBean immutableBean = (TargetBean) ImmutableBean.create(bean);
        System.out.println(immutableBean.getValue());
        bean.setValue("hello world again");
        System.out.println(bean.getValue());
        System.out.println(immutableBean.getValue());
        immutableBean.setValue("hello cglib");

    }

    /**
     * 操作bean的工具，在运行时动态创建一个bean
     * @author shaoyx
     * @return
     */
    @Test
    public void testBeanGenerator(){
        try{

            BeanGenerator beanGenerator = new BeanGenerator();
            beanGenerator.addProperty("value", String.class);
            Object bean = beanGenerator.create();

            Method setter = bean.getClass().getMethod("setValue", String.class);
            setter.invoke(bean, "hello cglib");

            Method getter = bean.getClass().getMethod("getValue");
            System.out.println(getter.invoke(bean));

        }catch (Exception e){

        }
    }

    @Test
    public void testBeanCopier(){
        try{
            // 设置为true，则使用converter
            BeanCopier copier = BeanCopier.create(TargetBean.class, TargetBean1.class, false);
            TargetBean targetBean = new TargetBean();
            targetBean.setValue("hello cglib");
            TargetBean1 targetBean1 = new TargetBean1();
            copier.copy(targetBean, targetBean1, null);

            System.out.println(targetBean1.getValue());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
