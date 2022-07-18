package com.zhang.juc.valotile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile
 * 保证可见性，不保证原子性，禁止指令重排
 * 如果不用volatile 和 synchronized 怎么保证原子性：可以使用原子类
 */
public class ValotileDemo {

    private volatile static int num = 0;
    private static void add() {
        num++;
    }

    private static AtomicInteger atomicInteger = new AtomicInteger();
    private static void atomicAdd(){
        atomicInteger.getAndIncrement();
    }



    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    atomicAdd();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(atomicInteger.get());
    }
}
