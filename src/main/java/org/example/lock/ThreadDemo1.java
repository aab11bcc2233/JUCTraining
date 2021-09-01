package org.example.lock;

import java.util.ArrayList;
import java.util.UUID;

/**
 * 集合 线程不安全演示
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(
                    () -> {
                        list.add(UUID.randomUUID().toString().substring(0, 4));
                        System.out.println(list);
                    }
            ).start();
        }
    }
}
