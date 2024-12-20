package com.example.demo;

import com.example.demo.sulaoban.Crypto;

/**
 * @author wuzhenhong
 * @date 2024/12/19 13:44
 */
public class Person {

    @Crypto
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
