package com.syx.springboot.inredis.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

@Configuration
@Import({ImportSelectImpl.class})
public class ImportSelectorConfig  {

}
