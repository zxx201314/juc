package com.zhang.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 600; i++) {
                    ticket.sale();
                }

            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 600; i++) {
                    ticket.sale();
                }
            }
        }, "B").start();
        ;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 600; i++) {
                    ticket.sale();
                }
            }
        }, "C").start();
        ;
    }

}

class Ticket {
    private int numbers = 500;
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        if (numbers > 0)
            System.out.println("綫程：" + Thread.currentThread().getName() + "卖出去第：" + (500 - numbers) + "票，剩余：" + numbers-- + "张票");
        lock.unlock();
    }
}