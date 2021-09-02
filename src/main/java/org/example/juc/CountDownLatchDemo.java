package org.example.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    // 6 位同学离开后，班长锁门
    public static void main(String[] args) throws InterruptedException {
        int count = 6;

        CountDownLatch countDownLatch = new CountDownLatch(count);
        
        for (int i = 1; i <= count; i++) {
            new Thread(
                () -> {
                    System.out.println(String.format("%s 号同学离开教室。", Thread.currentThread().getName()));

                    countDownLatch.countDown();
                },
                String.valueOf(i)
            ).start();
        }

        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + " 班长锁门");
    }
}
