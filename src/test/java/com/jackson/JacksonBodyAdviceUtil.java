package com.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @author wuzhenhong
 * @date 2024/12/23 10:47
 */
public class JacksonBodyAdviceUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        SimpleModule module = new SimpleModule();
        // 然后这个传方法值，可以通过线程上下文传过去，这里只是demo就这么写了，得优化的
        module.addSerializer(String.class, new CustomStringSerializer());
        OBJECT_MAPPER.registerModule(module);
    }

    public static final String writeValueAsString(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
