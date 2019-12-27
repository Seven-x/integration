package com.syx.springboot.inmq.rocket.config;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Properties;

/**
 * 阿里RocketMQ配置
 *
 * @author shaoyx
 * @date 16:57  2019/12/25
 */
@Configuration
@Data
@Slf4j
public class RocketMqConfig {

    /**
     * 生产者属性配置
     */
    @Bean
    @ConfigurationProperties(prefix = "aliyun.rocket-mq")
    public RocketMqProperties rocketMqProperties(){
        return new RocketMqProperties();
    }

    @Primary
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ProducerBean producerBean(){
        ProducerBean producerBean = new ProducerBean();
        Properties properties = new Properties();
        RocketMqProperties rocketMqProperties = this.rocketMqProperties();
        // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.AccessKey, rocketMqProperties.getAccessKey());
        // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.SecretKey, rocketMqProperties.getSecretKey());
        // 设置 TCP 接入域名（此处以公共云生产环境为例）
        properties.put(PropertyKeyConst.NAMESRV_ADDR, rocketMqProperties.getNameSrvAddr());
        producerBean.setProperties(properties);
        return producerBean;
    }


}
