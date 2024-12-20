package com.example.demo;

import org.springframework.context.annotation.Bean;

/**
 * @author wuzhenhong
 * @date 2022/8/5 14:37
 */
public class UserFactory {

    @Bean
    public User getUser() {
        return new User();
    }

}
