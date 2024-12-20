package com.example.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author wuzhenhong
 * @date 2023/1/31 15:11
 */
public class TTTTTTTTTTT {

    public static void main(String[] args){
        ScheduledExecutorService e = Executors.newScheduledThreadPool(0);
        e.schedule(() -> {
            System.out.println("业务逻辑");
        }, 60, TimeUnit.SECONDS);
        e.shutdown();
    }
}
