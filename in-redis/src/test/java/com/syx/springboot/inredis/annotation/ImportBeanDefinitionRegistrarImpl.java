package com.syx.springboot.inredis.annotation;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class ImportBeanDefinitionRegistrarImpl implements ImportBeanDefinitionRegistrar {

    /**
     * 手动注册一个Bean到Ioc容器中
     * @author syx
     * @param annotationMetadata
     * @param beanDefinitionRegistry
     * @return void
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(PersionOnImportBeanDefinitionRegistrar.class);
        // 注册一个叫persionOnImportBeanDefinitionRegistrar 的bean
        beanDefinitionRegistry.registerBeanDefinition("persionOnImportBeanDefinitionRegistrar", rootBeanDefinition);
    }
}
