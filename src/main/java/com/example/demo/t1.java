package com.example.demo;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author wuzhenhong
 * @date 2024/5/11 16:30
 */
public class t1 {

    public static void main(String[] args) {

        String str = Arrays.asList("1", "2").stream().collect(Collectors.joining());
        System.out.println(str);
    }

}
