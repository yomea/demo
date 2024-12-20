package com.example.demo;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuzhenhong
 * @date 2022/8/5 14:34
 */
@Configuration
public class FactoryBeanTest implements FactoryBean<UserFactory> {

    @Override
    public UserFactory getObject() throws Exception {
        return new UserFactory();
    }

    @Override
    public Class<UserFactory> getObjectType() {
        return UserFactory.class;
    }

    @Bean
    public User getUser() {
        return new User();
    }
}
