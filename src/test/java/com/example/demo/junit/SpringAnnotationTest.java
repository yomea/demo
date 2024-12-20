package com.example.demo.junit;

import cn.hutool.json.JSONUtil;
import com.example.demo.fastjson.User;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @author wuzhenhong
 * @date 2024/8/14 9:52
 */
@BBB(cc = "jjj")
public class SpringAnnotationTest {

    @Test
    public void t1() {
        AAA autoConfiguration = AnnotationUtils.getAnnotation(SpringAnnotationTest.class, AAA.class);
        AAA configuration = AnnotatedElementUtils.findMergedAnnotation( SpringAnnotationTest.class, AAA.class);
        AAA configuration2 = AnnotatedElementUtils.getMergedAnnotation( SpringAnnotationTest.class, AAA.class);
        System.out.println(configuration.value());
    }

    @Test
    public void t2() {
        User user = new User();
        user.setAge(10);
        user.setMony(BigDecimal.ZERO);
        String jsonStr = JSONUtil.toJsonStr(user);
        user.setName(jsonStr);
        System.out.println(JSONUtil.toJsonStr(user));
    }

}
