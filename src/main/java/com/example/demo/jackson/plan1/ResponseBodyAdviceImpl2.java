package com.example.demo.jackson.plan1;

import com.example.demo.jackson.plan2.ThreadLocalUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author wuzhenhong
 * @date 2024/12/12 8:22
 */
@ControllerAdvice()
public class ResponseBodyAdviceImpl2 implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response) {
        // 将参数返回类型保存到线程上下文中，通常调用到这里，接下来就是调用对应的序列化方法写出去了，一般不会发生报错
        // 所以在对应的序列化工具里remove即可
        ThreadLocalUtil.set(returnType);
        return body;
    }

}