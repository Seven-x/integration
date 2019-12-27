package com.syx.springboot.inmq.rocket.config;

import com.aliyun.openservices.ons.api.MessageListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 阿里云 RocketMQ 的消费者订阅设置
 *
 * @author shaoyx
 * @date 17:44  2019/12/25
 */
@Data
@AllArgsConstructor
public class RocketMqConsumerProperties {

    /**
     * 阿里云 RocketMQ 连接信息
     */
    private RocketMqProperties properties;
    /**
     * 阿里云 RocketMQ Group ID
     */
    private String groupId;

    /**
     *  阿里云 消费者明细列表
     */
    private List<ConsumerDetail> consumerDetailList;

}

@Data
@AllArgsConstructor
class ConsumerDetail {
    /**
     * 阿里云 订阅的 TOPIC
     */
    private String topic;
    /**
     * 阿里云 订阅的 TAGS
     */
    private String[] tags;
    /**
     * 阿里云 订阅的监听者
     */
    private MessageListener listener;
}

