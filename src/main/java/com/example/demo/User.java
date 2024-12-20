package com.example.demo;

import java.util.List;

/**
 * @author wuzhenhong
 * @date 2022/8/5 14:35
 */
public class User extends Person {

    private Integer age;

    private Address address;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
