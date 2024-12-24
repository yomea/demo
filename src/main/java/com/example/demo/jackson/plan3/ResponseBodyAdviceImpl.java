package com.example.demo.jackson.plan3;

import java.io.IOException;
import java.io.OutputStream;
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
public class ResponseBodyAdviceImpl implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response) {

        try (OutputStream outputStream = response.getBody()) {
            ThreadLocalUtil.set(returnType);
            String json = JacksonBodyAdviceUtil.writeValueAsString(body);
            response.getHeaders().add("Content-Type", selectedContentType.toString());
            // 直接将json字符串写出
            outputStream.write(json.getBytes());
            response.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            ThreadLocalUtil.remove();
        }
        // 返回null，让spring框架不要再处理返回值
        return null;
    }

}