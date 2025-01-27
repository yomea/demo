package com.example.demo;

import com.google.common.collect.Maps;
import java.util.Map;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuzhenhong
 * @date 2024/5/11 16:30
 */
public class t1 {

    public static void main(String[] args) {
//        String str = Arrays.asList("1", "2").stream().collect(Collectors.joining());
//        System.out.println(str);
        Object value = AnnotatedElementUtils.hasAnnotation(TestController2.class, Controller.class);
        Controller controller = AnnotationUtils.getAnnotation(TestController2.class, Controller.class);
        Controller controlle2r = AnnotationUtils.getAnnotation(TestController.class, Controller.class);
        System.out.println(value);
        System.out.println(controller);
        System.out.println(controlle2r);
        GetMapping proxy = AnnotationUtils.synthesizeAnnotation(GetMapping.class);
//        AnnotationUtils.findAnnotation();
//        AnnotationUtils.getAnnotationAttributes();
//        AnnotationUtils.synthesizeAnnotation()
        Map<String, Object> map = Maps.newHashMap();
        map.put("value", "1");
        RestController xx = AnnotationUtils.synthesizeAnnotation(map, RestController.class, TestController.class);
        Controller yy = AnnotatedElementUtils.findMergedAnnotation(TestController.class, Controller.class);
//        AnnotationAttributes uu = AnnotationUtils.getAnnotationAttributes(TestController.class, Controller.class);
        System.out.println(yy.value());
    }

}
