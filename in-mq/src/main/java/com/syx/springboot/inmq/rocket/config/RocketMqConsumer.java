package com.syx.springboot.inmq.rocket.config;

import com.aliyun.openservices.ons.api.Consumer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 阿里云 RocketMQ 的消费者订阅
 *
 * @author shaoyx
 * @date 17:49  2019/12/25
 */
@Slf4j
public class RocketMqConsumer {

    public RocketMqConsumer(List<RocketMqConsumerProperties> consumerPropertiesList) {
        this.consumerPropertiesList = consumerPropertiesList;
    }

    /**
     * 阿里云 RocketMQ 的消费者订阅设置
     */
    private List<RocketMqConsumerProperties> consumerPropertiesList;

    /**
     * 消息消费者接口，用来订阅消息
     */
    @Getter
    private List<Consumer> consumers;

    public RocketMqConsumer build(){

        return null;
    }
}
