package com.syx.springboot.inmq.rocket.client;

import com.aliyun.openservices.ons.api.Message;

/**
 * @author shaoyx
 * @date 16:46  2019/12/27
 */
public interface HandleMessage {

    /**
     * 消息处理方法
     *
     * @param message
     */
    void handleMessage(Message message);
}
