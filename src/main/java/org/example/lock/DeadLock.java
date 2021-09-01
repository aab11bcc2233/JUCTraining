package org.example.lock;

import java.util.concurrent.TimeUnit;

public class DeadLock {

    static Object a = new Object();
    static Object b = new Object();

    public static void main(String[] args) {
        new Thread(
                () -> {
                   synchronized (a) {
                       System.out.println(Thread.currentThread().getName() + " 持有锁 a，试图获取锁 b。");

                       try {
                           TimeUnit.SECONDS.sleep(2);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       synchronized (b) {
                           System.out.println(Thread.currentThread().getName() + " 获取到锁 b。");
                       }
                   }
                },
                "线程 A"
        ).start();

        new Thread(
                () -> {
                    synchronized (b) {
                        System.out.println(Thread.currentThread().getName() + " 持有锁 b，试图获取锁 a。");

                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (a) {
                            System.out.println(Thread.currentThread().getName() + " 获取到锁 a。");
                        }
                    }
                },
                "线程 B"
        ).start();
    }
}
