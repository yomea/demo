package com.example.demo.test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author wuzhenhong
 * @date 2023/5/4 13:28
 */
public class BBBtest {

    public static void main(String[] args) throws NoSuchFieldException {

        BBB instance = new BBB();
        Field field = instance.getClass().getSuperclass().getDeclaredField("b");
        Type type = field.getGenericType();
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            Type[] actualTypes = pType.getActualTypeArguments();
            if (actualTypes != null && actualTypes.length > 0) {
                Class<?> clazz = (Class<?>) actualTypes[1];
                System.out.println(clazz); // 输出：class java.lang.Number
            }
        }

    }

}
