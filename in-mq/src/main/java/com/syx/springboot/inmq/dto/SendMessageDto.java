package com.syx.springboot.inmq.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author shaoyx
 * @date 16:51  2019/12/27
 */
@Data
public class SendMessageDto {
    /**
     * 消息发送的topic
     */
    private String topic;
    /**
     * 消息发送的tag
     */
    private String tags;
    /**
     * 消息体，json字符串
     */
    private String messageBody;
    /**
     * 业务key
     */
    private String key;
    /**
     * 是否要抛出发送失败的异常,1要抛出，0不抛出，默认为0
     */
    private Boolean isThrowSendFailException = false;

    /**
     * 消息需要被投递的时间，发送延时消息时使用
     * 如果设置了当前时间之前的时间则会立刻发送
     */
    private Date startDeliverTime;
}
