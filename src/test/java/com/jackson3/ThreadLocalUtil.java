package com.jackson3;

import org.springframework.core.MethodParameter;

/**
 * @author wuzhenhong
 * @date 2024/12/20 10:37
 */
public class ThreadLocalUtil {

    private static final ThreadLocal<MethodParameter>
    THREAD_LOCAL = new ThreadLocal<>();

    public static void set(MethodParameter returnType) {
        THREAD_LOCAL.set(returnType);
    }

    public static MethodParameter get() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
         THREAD_LOCAL.remove();
    }
}
