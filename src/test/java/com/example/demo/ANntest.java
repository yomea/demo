package com.example.demo;

import java.lang.reflect.Method;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wuzhenhong
 * @date 2023/12/23 16:51
 */
public class ANntest {

    @GetMapping("aa/aa")
    public void t() {

    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = ANntest.class.getMethod("t");
        RequestMapping getMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
        System.out.println(((String[])AnnotationUtils.getAnnotationAttributes(method, getMapping).get("path"))[0]);
    }
}
