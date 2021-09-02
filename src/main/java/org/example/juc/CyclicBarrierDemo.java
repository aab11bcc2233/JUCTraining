package org.example.juc;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    private static final int NUMBER = 7;

    // 集齐7颗龙珠，才可以召唤神龙
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println("神龙显");
        });

        for (int i = 1; i <= NUMBER; i++) {
            new Thread(() -> {
                try {
                    System.out.println(String.format("集齐第 %s 颗龙珠", Thread.currentThread().getName()));
                    cyclicBarrier.await();
                    System.out.println(String.format("线程 %s 结束", Thread.currentThread().getName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
