package org.example.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Share {
    private int num;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void incr() throws InterruptedException {
        lock.lock();

        try {

            while (num != 0) {
                condition.await();
            }

            num += 1;
            System.out.println("ThreadName = " + Thread.currentThread().getName() + " -- num = " + num);

            condition.signalAll();

        } finally {
            lock.unlock();
        }
    }
    public void decr() throws InterruptedException {
        lock.lock();

        try {

            while (num != 1) {
                condition.await();
            }

            num -= 1;
            System.out.println("ThreadName = " + Thread.currentThread().getName() + " -- num = " + num);

            condition.signalAll();

        } finally {
            lock.unlock();
        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        Share share = new Share();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }
}
