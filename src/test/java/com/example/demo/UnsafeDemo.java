/*
package com.example.demo;

import com.alibaba.fastjson.JSON;
import java.lang.reflect.Field;
import sun.misc.Unsafe;

*/
/**
 * @author: sucf
 * @date: 2022/10/9 15:20
 * @description:
 *//*

public class UnsafeDemo {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Unsafe unsafe = getUnsafe();
        A a = new A("1", 1);
        Field field = A.class.getDeclaredField("s");
        long offset = unsafe.objectFieldOffset(field);
        System.out.println(unsafe.compareAndSwapObject(a, offset, "1", "2"));
        System.out.println(JSON.toJSONString(a));

    }

    static class A {

        public A(String s, int i) {
            this.s = s;
            this.i = i;
        }

        private String s;
        private int i;

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }

    public static Unsafe getUnsafe() throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        return (Unsafe) unsafeField.get(null);
    }
}*/
