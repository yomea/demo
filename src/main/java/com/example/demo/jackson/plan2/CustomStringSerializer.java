package com.example.demo.jackson.plan2;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Objects;
import org.springframework.core.MethodParameter;
import org.springframework.util.ReflectionUtils;

public class CustomStringSerializer extends StdScalarSerializer<String> {

    public CustomStringSerializer() {
        super(String.class, false);
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

    @Override
    public boolean isEmpty(SerializerProvider prov, String value) {
        return value == null || value.isEmpty();
    }

    @Override
    public final void serializeWithType(String value, JsonGenerator gen, SerializerProvider provider,
        TypeSerializer typeSer) throws IOException
    {
        gen.writeString(value);
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return createSchemaNode("string", true);
    }

    @Override
    public void acceptJsonFormatVisitor(
        JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        visitStringFormat(visitor, typeHint);
    }
}