package com.syx.springboot.inredis.test.push;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author syx
 * @date 14:57  2019/10/15
 * 极光推送
 */
public class JSMSExample {

    private static final Logger log = LoggerFactory.getLogger(JSMSExample.class);

    private static final String appkey = "242780bfdd7315dc1989fe2b";
    private static final String masterSecret = "2f5ced2bef64167950e63d13";

    private static final String devKey = "242780bfdd7315dc1989fedb";
    private static final String devSecret = "2f5ced2bef64167950e63d13";

    public static void main(String[] args){
        testSendSMSCode();
    }


    public static void testSendSMSCode(){
        SMSClient client = new SMSClient(masterSecret, appkey);
        SMSPayload payload =SMSPayload.newBuilder()
                .setMobileNumber("17759728023")
                .setTempId(1)
                .build();
        try{
            SendSMSResult result = client.sendSMSCode(payload);
            System.out.println(result.toString());
        }catch (APIConnectionException e){
            log.error("Connection error. Should retry later. ", e);
        }catch (APIRequestException e){
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Message: " + e.getMessage());
        }
    }
}
