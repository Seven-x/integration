package com.syx.springboot.inmq.exception;


/**
 * @author shaoyx
 * @date 17:15  2019/12/27
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
