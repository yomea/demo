package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wuzhenhong
 * @date 2022/8/5 16:35
 */
@Component
public class B {

    @Autowired
    private A a;

}
