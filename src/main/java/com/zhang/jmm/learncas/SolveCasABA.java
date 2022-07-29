package com.zhang.jmm.learncas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

@Slf4j
public class SolveCasABA {
    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(() -> {
            int stamp = myData.atomicStampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = myData.atomicStampedReference.compareAndSet(1, 2, stamp, stamp + 1);
            log.info(Thread.currentThread().getName() + ">>>>>>>" + b + "\t" + myData.atomicStampedReference.getReference());
            boolean c = myData.atomicStampedReference.compareAndSet(2, 1, myData.atomicStampedReference.getStamp(), myData.atomicStampedReference.getStamp() + 1);
            log.info(Thread.currentThread().getName() + ">>>>>>>" + b + "\t" + myData.atomicStampedReference.getReference());
        }, "T1").start();

        new Thread(() -> {
            int stamp = myData.atomicStampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = myData.atomicStampedReference.compareAndSet(1, 2, stamp, stamp + 1);
            log.info(Thread.currentThread().getName() + ">>>>>>>" + b + "\t" + myData.atomicStampedReference.getReference());
        }, "T2").start();
    }
}

class MyData {
    AtomicInteger atomicInteger = new AtomicInteger();
    AtomicStampedReference atomicStampedReference = new AtomicStampedReference(1, 1);
}