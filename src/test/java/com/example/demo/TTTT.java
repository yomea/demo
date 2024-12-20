package com.example.demo;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author wuzhenhong
 * @date 2022/10/28 14:03
 */
public class TTTT {

    public static void main(String[] args) {
        DecimalFormat format = new DecimalFormat("0.00000");

        BigDecimal num = new BigDecimal(String.valueOf(BigDecimal.ZERO));

        System.out.println(format.format(num));
    }

}
