package com.jackson;

import java.util.List;

/**
 * @author wuzhenhong
 * @date 2024/12/23 14:14
 */
public class Cat extends Animal{

    private Integer age;

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
}
