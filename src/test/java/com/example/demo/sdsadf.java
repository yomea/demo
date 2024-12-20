package com.example.demo;

import java.time.ZoneId;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.PropertyPlaceholderHelper.PlaceholderResolver;

/**
 * @author wuzhenhong
 * @date 2024/11/14 16:49
 */
public class sdsadf {

    public static void main(String[] args) {

        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
        String str = helper.replacePlaceholders("${ab} == 'a'", new PlaceholderResolver() {
            @Override
            public String resolvePlaceholder(String placeholderName) {
                return placeholderName;
            }
        });
        System.out.println(str);
    }

}
