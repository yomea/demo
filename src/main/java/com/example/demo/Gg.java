package com.example.demo;

import java.util.TreeMap;

/**
 * @author wuzhenhong
 * @date 2024/2/29 16:44
 */
public class Gg {

    public static void main(String[] args) {

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "a");
        treeMap.put(5, "f");

        System.out.println(treeMap.get(treeMap.floorKey(5)));


    }

}
