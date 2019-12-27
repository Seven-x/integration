package com.syx.springboot.inmq.rocket.config;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Properties;

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

    /**
     * 构建多个消费者
     *
     * @param
     * @return
     */
    public RocketMqConsumer build() {
        this.consumers = Lists.newArrayList();
        for (RocketMqConsumerProperties consumerProperties : consumerPropertiesList) {
            String groupId = consumerProperties.getGroupId();

            // 链接配置
            Properties properties = new Properties();
            RocketMqProperties connProperties = consumerProperties.getProperties();
            properties.setProperty(PropertyKeyConst.SecretKey, connProperties.getSecretKey());
            properties.setProperty(PropertyKeyConst.AccessKey, connProperties.getAccessKey());
            properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, connProperties.getNameSrvAddr());
            properties.setProperty(PropertyKeyConst.GROUP_ID, groupId);

            // 绑定监听的 topic
            Consumer consumer = ONSFactory.createConsumer(properties);
            for (ConsumerDetail consumerDetail : consumerProperties.getConsumerDetailList()){
                String topic = consumerDetail.getTopic();
                String[] tags = consumerDetail.getTags();

                // 绑定要监听的tag，多个tag用 || 隔开
                consumer.subscribe(topic, StringUtils.join(tags, "||"), consumerDetail.getListener());
                log.info("阿里云 启动消费者 GROUP_ID-{} TOPIC-{} 成功!", groupId, topic);
            }

            consumer.start();
            this.consumers.add(consumer);
        }
        log.info("阿里云 启动{}个消费者。", this.consumers.size());
        return this;
    }
}
