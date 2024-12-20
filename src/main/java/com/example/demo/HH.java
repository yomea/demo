package com.example.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuzhenhong
 * @date 2023/10/11 16:53
 */
public class HH {


    public static String process(String s) {
        Character temp = null;
        int count = 0;
        Map<Character, Boolean> map = new HashMap<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)){
                if (!map.get(c)) {
                    result.append(c);
                }
                break;
            }else{
                if (temp == null || c == temp.charValue()) {
                    count++;
                    temp = c;
                } else {
                    if (count % 2 != 0) {
                        // 奇数  要被删的
                        map.put(temp, true);
                    } else {
                        map.put(temp, false);
                    }
                    temp = null;
                    count = 0;
                }
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String test = "baabbaa";
        String output = HH.process(test);
        System.out.println(output);
    }

}
