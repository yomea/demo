package com.example.demo.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

// 示例类
class User {
    private String name;
    private int age;

    // 构造函数、getter 和 setter 方法
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

// 自定义序列化器
class UserSerializer implements JsonSerializer<Object> {

    String[] a;
    public UserSerializer(String[] a) {
        this.a = a;
    }
    @Override
    public JsonElement serialize(Object obj, Type type, JsonSerializationContext context) {
        System.out.println(a[0]);
        System.out.println();
        return null;
    }
}

public class Main {
    public static void main(String[] args) {

        String[] a = new String[1];
        User user = new User("Alice", 30);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingStrategy(f -> {
            String name = f.getName();
            a[0] = name;
            return name;
        });
        gsonBuilder.registerTypeAdapter(String.class, new UserSerializer(a));
        Gson gson = gsonBuilder.create();

        String json = gson.toJson(user);
        System.out.println(json);  // 输出: {"name":"ALICE","age":30}
    }
}