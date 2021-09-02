package org.example.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    
    // 6 辆车，抢 3个 车位。
    public static void main(String[] args) {
        // 设置许可数量，也就是 3个 车位
        Semaphore semaphore = new Semaphore(3);
        
        for (int i = 1; i <= 6; i++) {
            new Thread(
                () -> {
                    // 抢占
                    try {
                        semaphore.acquire();
                        
                        System.out.println(String.format("%s 号车抢到了车位。", Thread.currentThread().getName()));

                        int time = new Random().nextInt(5);
                        TimeUnit.SECONDS.sleep(time);

                        System.out.println(String.format("------------------ %s 号车 %d 秒后离开了车位。", Thread.currentThread().getName(), time));

                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } finally {
                        // 释放
                        semaphore.release();
                    }
                },
                String.valueOf(i)
            ).start();
        }
    }
}
