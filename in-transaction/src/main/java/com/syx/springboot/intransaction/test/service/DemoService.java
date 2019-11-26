package com.syx.springboot.intransaction.test.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shaoyx
 * @date 14:37  2019/11/26
 */
@Service
public class DemoService {

    /**
     *
     * @author shaoyx
     * @param
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void demoAdd(){
        System.out.println(1);
    }
}
