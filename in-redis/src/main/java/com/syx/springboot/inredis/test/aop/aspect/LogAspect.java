package com.syx.springboot.inredis.test.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author shaoyx
 * @date 14:05  2019/12/5
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(public * com.syx.springboot.inredis.test.aop.aspect.AspectDemo.aspect())")
    public void logAspect(){};

    @Before("logAspect()")
    public void doBefore(){
        System.out.println("do before");
    }

    @After("logAspect()")
    public void doAfter(){
        System.out.println("do after");
    }

    @AfterReturning(value = "logAspect()", returning = "retVal")
    public void doAfterReturning(Object retVal){
        System.out.println("do after return");
    }

    @AfterThrowing(value = "logAspect()", throwing = "exception")
    public void doAfterThrowing(Exception exception){
        System.out.println("do after thrown");
    }

    @Around("logAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("do Around");
            return joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            System.out.println("do Around");
        }
    }
}
