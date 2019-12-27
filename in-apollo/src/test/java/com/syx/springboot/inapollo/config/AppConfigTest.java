package com.syx.springboot.inapollo.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author shaoyx
 * @date 14:00  2019/12/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppConfigTest {

    @Autowired
    private AppConfig appConfig;

    @Test
    public void getInfoFromApollo() {
        System.out.println(appConfig.getPersonName());
        System.out.println(appConfig.getPersonId());
    }

    @Test
    public void getInfoFromApolloApi(){
        Config config = ConfigService.getAppConfig();
        String personName = config.getProperty("application", "personName");
        System.out.println(personName);
    }
}