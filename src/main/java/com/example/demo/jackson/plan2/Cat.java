package com.example.demo.jackson.plan2;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wuzhenhong
 * @date 2024/12/23 14:14
 */
public class Cat extends Animal{

    private Integer age;

    private LocalDateTime birth;

    private List<Animal> children;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Animal> getChildren() {
        return children;
    }

    public void setChildren(List<Animal> children) {
        this.children = children;
    }

    public LocalDateTime getBirth() {
        return birth;
    }

    public void setBirth(LocalDateTime birth) {
        this.birth = birth;
    }
}
