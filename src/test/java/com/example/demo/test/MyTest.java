package com.example.demo.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * bug测试
 */
public class MyTest {

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(64, 64,
        0, TimeUnit.MINUTES,
        new ArrayBlockingQueue<>(32));

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            CountDownLatch countDownLatch = new CountDownLatch(34);
            for (int j = 0; j < 34; j++) {
                threadPoolExecutor.execute(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                });
            }
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("都执行完成了");
        }
    }
}