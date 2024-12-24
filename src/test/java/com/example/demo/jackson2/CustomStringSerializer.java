package com.example.demo.jackson2;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.jackson.Sensitive;
import com.jackson.SensitiveComplex;
import com.jackson.ThreadLocalUtil;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;
import org.springframework.core.MethodParameter;
import org.springframework.util.ReflectionUtils;

public class CustomStringSerializer extends StdSerializer<String> {

    public CustomStringSerializer() {
        super(String.class);
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        // 获取到当前要解析到的字段的名字
        String name = gen.getOutputContext().getCurrentName();
        Class<?> currentParseObj = gen.getOutputContext().getCurrentValue().getClass();
        Field field = ReflectionUtils.findField(currentParseObj, name);
        Sensitive crypto = field.getAnnotation(Sensitive.class);
        if (Objects.nonNull(crypto)) {
            gen.writeStartObject();
            gen.writeStringField(name + "脱敏", "**" + value + "**");
            gen.writeStringField(name + "密码", "--" + value + "--");
            gen.writeEndObject();
            return;
        }
        MethodParameter returnType = ThreadLocalUtil.get();
        // 在这个返回类型的注解
        SensitiveComplex cryptoComplex = returnType.getMethodAnnotation(SensitiveComplex.class);
        if (Objects.isNull(cryptoComplex)) {
            gen.writeString(value);
            return;
        }
        String[] fieldNames = cryptoComplex.value();
        if (fieldNames == null || fieldNames.length == 0) {
            gen.writeString(value);
            return;
        }

        for (String fieldName : fieldNames) {
            if (name.equals(fieldName)) {
                gen.writeStartObject();
                gen.writeStringField(name + "脱敏", "**" + value + "**");
                gen.writeStringField(name + "密码", "--" + value + "--");
                gen.writeEndObject();
                break;
            }
        }
    }
}