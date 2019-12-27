package com.syx.springboot.inmq.rocket.config;

import lombok.Data;

/**
 * 阿里云 RocketMQ 连接信息
 * @author shaoyx
 * @date 17:03  2019/12/25
 */
@Data
public class RocketMqProperties {

    /**
     * 阿里云身份验证 RocketMQ AccessKey
     */
    private String accessKey;

    /**
     * 阿里云身份验证 RocketMQ SecretKey
     */
    private String secretKey;

    /**
     * 阿里MQ实例 AutoSaas TCP 协议接入点
     */
    private String nameSrvAddr;
}
