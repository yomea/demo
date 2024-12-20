package com.example.demo.proxy;

/**
 * @author wuzhenhong
 * @date 2023/10/16 15:38
 */
public class TT implements T{

    @Override
    public void print() {
        System.out.println("hello dynamic proxy");
    }
}
