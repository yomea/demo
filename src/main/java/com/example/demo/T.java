package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import org.apache.ibatis.ognl.Ognl;

/**
 * @author wuzhenhong
 * @date 2024/12/25 16:13
 */
public class T {

    public static class Base {
//        private Long id;
    }

    public static class User extends Base {
        private Long id;

        public void setId(Long id) {
            this.id = id;
        }

    }

    public static void main(String[] args) throws Throwable {

        User user = new User();
        user.setId(1L);
        System.out.println(new Gson().toJson(user));

        XX xx= new XX();
        xx.setA("a");
        xx.setB(1L);
        Map<String, Object> context = new HashMap<>();
        context.put("a", xx);
        Object value = Ognl.getValue("a.a + a.b", context);
        System.out.println(value);
    }

    @Data
    public static class XX {
        private String a;
        private Long b;
    }
}
