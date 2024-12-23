package com.jackson;

/**
 * @author wuzhenhong
 * @date 2024/12/23 14:14
 */
public class Animal {

    @Sensitive
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
