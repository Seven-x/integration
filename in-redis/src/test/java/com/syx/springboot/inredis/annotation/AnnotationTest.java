package com.syx.springboot.inredis.annotation;

import com.syx.springboot.inredis.InRedisApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author EDZ
 */

public class AnnotationTest extends InRedisApplicationTests {

    @Autowired
    private PersionOnValue persionOnValue;

    @Autowired
    private PersionOnConfigurationProperties persionOnConfigurationProperties;

    @Test
    public void testOnValue() {
        System.out.println("@Value注解：" + persionOnValue.toString());
        System.out.println("@ConfigurationProperties注解：" + persionOnConfigurationProperties.toString());
    }

    @Test
    public void testOnImport(){
        ApplicationContext context = new AnnotationConfigApplicationContext(ImportConfig.class);
        PersionOnImport onImport = context.getBean(PersionOnImport.class);
        onImport.sayHi();

        PersionOnImportSelector importSelector = context.getBean(PersionOnImportSelector.class);
        importSelector.sayHi();

        PersionOnImportBeanDefinitionRegistrar importBDR = context.getBean(PersionOnImportBeanDefinitionRegistrar.class);
        importBDR.sayHi();
    }



}
