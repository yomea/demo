package com.example.demo.junit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

/**
 * @author wuzhenhong
 * @date 2024/8/14 10:26
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AAA
public @interface BBB {

    @AliasFor(value = "value", annotation = AAA.class)
    String cc();
}
