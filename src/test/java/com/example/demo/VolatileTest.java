package com.example.demo;

import java.util.concurrent.CountDownLatch;
import sun.misc.Contended;

public class VolatileTest {

    static class Demo{
        private static long a1 = 0L;
        private static long a2 = 0L;
        private static long a3 = 0L;
        private static long a4 = 0L;
        private static long a5 = 0L;
        private static long a6 = 0L;
        private static long a7 = 0L;
        public static volatile long a = 0L;
        private static long b1 = 0L;
        private static long b2 = 0L;
        private static long b3 = 0L;
        private static long b4 = 0L;
        private static long b5 = 0L;
        private static long b6 = 0L;
        private static long b7 = 0L;
        public static volatile long b = 0L;
    }
    static Demo[] demos = new Demo[2];
    static {
        demos[0] = new Demo();
        demos[1] = new Demo();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10_000_000_0L;i++){
                    demos[0].a = i;
                }
                countDownLatch.countDown();
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10_000_000_0L;i++){
                    demos[1].b= i;
                }
                countDownLatch.countDown();
            }
        });
        long startTime = System.currentTimeMillis();
        threadB.start();
        threadA.start();
        countDownLatch.await();
        System.out.println(System.currentTimeMillis()-startTime);

    }


}