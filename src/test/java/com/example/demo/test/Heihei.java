package com.example.demo.test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author wuzhenhong
 * @date 2022/11/3 11:26
 */
public class Heihei {

    private static DateTimeFormatter YYYY_MM = DateTimeFormatter.ofPattern("yyyy-MM");

    public static void main(String[] args) {

//        LocalDate startLocalDate = LocalDate.from(YYYY_MM.parse("2022-05"));
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(1669080580832L), ZoneId.systemDefault());

        System.out.println();
    }

}
