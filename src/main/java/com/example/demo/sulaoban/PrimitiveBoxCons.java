package com.example.demo.sulaoban;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wuzhenhong
 * @date 2024/12/18 11:09
 */
public class PrimitiveBoxCons {

    public static final Set<Class> PRIMITIVE_SET;

    static {
        PRIMITIVE_SET = new HashSet<>();
        PRIMITIVE_SET.add(Byte.class);
        PRIMITIVE_SET.add(Short.class);
        PRIMITIVE_SET.add(Void.class);
        PRIMITIVE_SET.add(Character.class);
        PRIMITIVE_SET.add(Integer.class);
        PRIMITIVE_SET.add(Long.class);
        PRIMITIVE_SET.add(Float.class);
        PRIMITIVE_SET.add(Double.class);
    }

}
