package com.example.demo.jackson.plan3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author wuzhenhong
 * @date 2024/12/23 10:47
 */
@Component
public class JacksonBodyAdviceUtil implements ApplicationContextAware {

    private static  ObjectMapper OBJECT_MAPPER;


    public static final String writeValueAsString(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        OBJECT_MAPPER = applicationContext.getBean(ObjectMapper.class);
    }
}
