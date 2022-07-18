package com.zhang.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionDemo {
    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    data3.printA();
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    data3.printB();
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    data3.printC();
                }
            }
        }, "C").start();
    }
}

class Data3 {
    private Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();
    private int numbers = 1; //1A ,2B ,3C

    public void printA() {
        lock.lock();
        try {
            //业务->判断->执行->通知
            while (numbers != 1) {
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName() + "====>AAAAAAAAAAAAAAA");
            numbers = 2;
            conditionB.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (numbers != 2) {
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName() + "====>BBBBBBBBBBBBBB");
            numbers = 3;
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (numbers != 3) {
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName() + "====>CCCCCCCCCCCCCCCCCC");
            numbers = 1;
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}