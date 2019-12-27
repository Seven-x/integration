package com.syx.springboot.inmq.rocket.client;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.syx.springboot.inmq.dto.SendMessageDto;
import com.syx.springboot.inmq.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.UUID;

/**
 * @author shaoyx
 * @date 16:47  2019/12/27
 */
@Component
@Slf4j
public class ProduceService {

    @Autowired
    private ProducerBean producerBean;

    @Autowired
    private ApplicationEventPublisher publisher;

    /**
     * 发送同步消息
     *
     * @param messageDto
     * @return
     */
    public SendResult sendMessage(SendMessageDto messageDto) {
        Message message = buildMessage(messageDto);
        try {
            log.info("【MQ消息发送】同步发送mq消息开始, TAG:{}, message:{}", message.getTag(), JSON.toJSONString(message.getBody()));
            SendResult sendResult = producerBean.send(message);
            log.info("【MQ消息发送】同步发送mq消息结束, SendResult:{}", sendResult.toString());
            return sendResult;
        } catch (Exception e) {
            log.error("【MQ消息发送】同步发送mq消息结果失败, message:{}", message, e);
            if (messageDto.getIsThrowSendFailException()) {
                throw new RuntimeException("消息发送失败");
            } else {
                return null;
            }
        }
    }

    /**
     * 构建消息体
     *
     * @param dto
     * @return
     */
    private Message buildMessage(SendMessageDto dto) {
        String topic = dto.getTopic();
        String msgBody = dto.getMessageBody();
        if (StringUtils.isBlank(topic)){
            throw new RuntimeException("Topic is null");
        }
        if (StringUtils.isBlank(msgBody)){
            throw new ServiceException("msgBody is null");
        }
        Message message = new Message(topic, dto.getTags(), msgBody.getBytes());
        if (dto.getStartDeliverTime() != null){
            message.setStartDeliverTime(dto.getStartDeliverTime().getTime());
        }
        if (StringUtils.isNotBlank(dto.getKey())){
            message.setKey(dto.getKey());
        }

        Properties properties = new Properties();
        properties.put("lockKey", UUID.randomUUID().toString());
        message.setUserProperties(properties);
        return message;
    }
}
