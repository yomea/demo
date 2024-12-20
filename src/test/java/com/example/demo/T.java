package com.example.demo;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wuzhenhong
 * @date 2022/8/10 15:39
 */
public class T {

    public static void main(String[] args) throws Exception {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue(1);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(() -> {
            try {
                arrayBlockingQueue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            throw new OutOfMemoryError();
        }).start();

        arrayBlockingQueue.put(1);
    }

}
