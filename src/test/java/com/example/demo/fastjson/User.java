package com.example.demo.fastjson;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wuzhenhong
 * @date 2022/10/9 9:47
 */
public class User {

    private String name;
    private Integer age;
    private BigDecimal mony;
    private List<User> childs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getMony() {
        return mony;
    }

    public void setMony(BigDecimal mony) {
        this.mony = mony;
    }

    public List<User> getChilds() {
        return childs;
    }

    public void setChilds(List<User> childs) {
        this.childs = childs;
    }
}
