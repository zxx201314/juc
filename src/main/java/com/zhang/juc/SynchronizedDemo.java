package com.zhang.juc;

public class SynchronizedDemo {
    public static void main(String[] args) {
        Data data= new Data();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i <10 ; i++) {
                        data.increment();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i <10 ; i++) {
                        data.decrement();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

    }
}
 class Data {
    private int numbers = 0;

    public synchronized void increment() throws InterruptedException {
        while (numbers != 0) {
            this.wait();
        }
        numbers++;
        System.out.println(Thread.currentThread().getName() + "-->" + numbers);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException{
        while (numbers == 0) {
            this.wait();
        }
        numbers--;
        System.out.println(Thread.currentThread().getName() + "-->" + numbers);
        this.notifyAll();
    }
}
