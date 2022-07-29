package com.zhang.jmm.learnvolatile;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        //可见性验证代码
        /*new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addNumber();
            log.info(Thread.currentThread().getName() + ":" + myData.number);
        }, "AAA").start();

        while (myData.number == 0) {

        }
        log.info(Thread.currentThread().getName() + ":" + myData.number);*/

        //原子性验证代码,可以通过lock锁+volatile关键字保证其原子性
        Lock lock = new ReentrantLock();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addNumber();
                }
            }, String.valueOf(i)).start();
        }
        // Thread.activeCount() > 2 是因为后台有一个main线程和GC线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        log.info(String.valueOf(myData.number));
        log.info(String.valueOf(myData.atomicInteger));
    }
}

/**
 * 1.验证volatile的可见性
 *  1.1 假如int number = 0;number变量之前没有添加volatile关键字，没有可见性
 *  1.2 添加了volatile 之后保证了可见性
 * 2.验证volatile不保证原子性
 *  2.1 原子性：不可分割，完整性；某个线程在操作业务对象或属性时，中间不可被加塞或者分割，需要整体完整，要不成功，要不失败。
 * 3.如何保证原子性
 *  3.1 使用juc下面的atomic类
 */
class MyData {
    //保证线程可见性
    volatile int number = 0;
    //juc下面的atomic类能够保证原子性
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addNumber() {
        this.number = number + 1;
        atomicInteger.getAndIncrement();
    }

}