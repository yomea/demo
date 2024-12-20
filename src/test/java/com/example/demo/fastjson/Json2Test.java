/*
package com.example.demo.fastjson;

public class Json2Test {

    private static final char 左大括号 = '{';
    private static final char 右大括号 = '}';
    private static final char 左中括号 = '[';
    private static final char 右中括号 = ']';
    private static final char 双引号 = '"';
    private static final char 冒号 = ':';
    private static final char 逗号 = ',';


    public static void main(String[] args) {

        String json = "{\"name\":\"宝儿姐\",\"age\":18,\"childs\":[{\"name\":\"大壮\",\"age\":1},{\"name\":\"小美\",\"age\":10}],\"mony\":-4000000.5}";

        start(json);
    }

    private static int start(String json) {
        return start(json, 0);
    }

    private static int start(String json, int index) {
        char a = json.charAt(index);
        if(左大括号 == a) {
            return parseJavaBean(json, index + 1);
        } else if(左中括号 == a) {
            return parseJavaCollection(json, index + 1);
        } else if(双引号 == a) {
            return parseJavaString(json, index + 1);
        } else {
            // 可能是数字
            return parseJavaNumber(json, index + 1);
        }
    }

    private static int parseJavaBean(String json, int index) {
        char 左引号 = json.charAt(index);
        if(左引号 != 双引号 ) {
            throw new RuntimeException("json错误");
        }
        // 属性获取
        StringBuilder propertyName = new StringBuilder();
        boolean end = false;
        for(int i = index + 1; i < json.length(); i++) {
            char c = json.charAt(i);
            if(!end && 双引号 != c) {
                propertyName.append(c);
            } else if(双引号 == c) {
                end = true;
            } else if(end && 冒号 != c && ' ' != c) {
                throw new RuntimeException("错误的json语法");
            } else if(end && 冒号 == c) {
                start(json, i + 1);
            }
        }
    }

    private static int parseJavaCollection(String json, int index) {

    }

    private static int parseJavaString(String json, int index) {
        for(int i = 0; i < json.length(); i++) {

        }
    }

    private static int parseJavaNumber(String json, int index) {

    }
}
*/
