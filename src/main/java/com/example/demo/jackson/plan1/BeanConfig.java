package com.example.demo.jackson.plan1;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wuzhenhong
 * @date 2024/5/17 9:19
 */
@Configuration
public class BeanConfig implements WebMvcConfigurer {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder ->
            jacksonObjectMapperBuilder.simpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializerByType(String.class, new CustomStringSerializer());
        // 以下如果项目里已经注册过了，就不需要再提供，我们只提供处理脱敏的
                // long类型转string， 前端处理Long类型，数值过大会丢失精度
//                .serializerByType(Long.class, ToStringSerializer.instance)
//                .serializerByType(Long.TYPE, ToStringSerializer.instance)
//                .serializationInclusion(JsonInclude.Include.NON_NULL)
//                //指定反序列化类型，也可以使用@JsonFormat(pattern = "yyyy-MM-dd")替代。主要是mvc接收日期时使用
//                .deserializerByType(LocalDate.class,
//                    new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
//                .deserializerByType(LocalDateTime.class,
//                    new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
//                // 日期序列化，主要返回数据时使用
//                .serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
//                .serializerByType(LocalDateTime.class,
//                    new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ClearTlInter());
    }
}
