package com.syx.springboot.inredis.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author syx
 * @date 14:17  2019/9/27
 */
@Import({ImportBeanDefinitionRegistrarImpl.class})
@Configuration
public class ImportBeanDefinitionRegistrarConfig {
}
