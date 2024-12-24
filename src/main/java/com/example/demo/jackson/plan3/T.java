package com.example.demo.jackson.plan3;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.MethodParameter;

/**
 * @author wuzhenhong
 * @date 2024/12/12 9:11
 */
public class T {

    public void t() {

    }

    public static void main(String[] args) throws JsonProcessingException, NoSuchMethodException {

        Cat cat = new Cat();
        cat.setName("加菲猫");
        cat.setAge(1);

        Cat cat1 = new Cat();
        cat1.setName("加菲猫1号");
        cat1.setAge(0);

        Cat cat2 = new Cat();
        cat2.setName("加菲猫1号");
        cat2.setAge(0);

        List<Animal> catList = new ArrayList<>();
        catList.add(cat1);
        catList.add(cat2);

        cat.setChildren(catList);

        MethodParameter returnType = new MethodParameter(T.class.getMethod("t"), -1);
        ThreadLocalUtil.set(returnType);
        String json = JacksonBodyAdviceUtil.writeValueAsString(cat);

        System.out.println(json);

    }

}
