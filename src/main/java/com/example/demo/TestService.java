package com.example.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author wuzhenhong
 * @date 2022/8/5 15:10
 */
@Service
public class TestService implements ApplicationContextAware, InitializingBean {

    @Value("${huawei.license.activate.url}")
    private String activateUrl;

    @Value("${huawei.license.heartbeat.url}")
    private String heartbeatUrl;

    @Value("${huawei.license.app.key}")
    private String appkey;

    @Value("${huawei.license.app.secrect}")
    private String appSecrect;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println();
    }
}
