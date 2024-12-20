package com.example.demo;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wuzhenhong
 * @date 2023/10/11 16:19
 */
public class La {

    private static class H {
        public int count;

        public boolean flag;

        public H(int count, boolean flag) {
            this.count = count;
            this.flag = flag;
        }
    }

    public static void main(String[] args) {

        System.out.println(("1").getClass().getName());

        String oldStr = "aabbbcdacdbbbb";
        // aabbbcda

        Map<Character, H> map = new LinkedHashMap<>();
        StringBuilder newStr = new StringBuilder();
        Character lastC = null;
        for(int i = 0; i < oldStr.length(); i++) {
            Character c = oldStr.charAt(i);
            H h = map.get(c);
            if(Objects.isNull(h)) {
                map.put(c, new H(1, true));
                newStr.append(c);
            } else {
                if(h.flag) {
                    h.count++;
                    newStr.append(c);
                } else if(h.count % 2 == 0) {
                    h.count++;
                    newStr.append(c);
                }
            }
            if(Objects.nonNull(lastC) && !lastC.equals(c)) {
                map.get(lastC).flag = false;
            }
            lastC = c;
        }

        System.out.println(newStr);
    }

}