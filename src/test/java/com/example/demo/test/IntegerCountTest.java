package com.example.demo.test;

/**
 * @author wuzhenhong
 * @date 2022/10/12 14:48
 */
public class IntegerCountTest {

    public static void main(String[] args) {

        int a = 100;
        int t = 0;
        int n = 0;
        t = a >> 16;
        if(t == 0) {
           t = a << 16;

        }

    }

    public static void method1() {
        int a = 100;

        System.out.println(String.valueOf(a).length());
    }
}
