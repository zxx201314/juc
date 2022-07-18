package com.zhang.thread;

public class TestThreadJoin implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 100; i++) {
            System.out.println("线程Vip来了");
        }
    }

    public static void main(String[] args) {
        TestThreadJoin testThreadJoin = new TestThreadJoin();
        Thread thread = new Thread(testThreadJoin);
        thread.start();
        for (int i = 0; i < 1000; i++) {
            if (i == 200) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("主线程执行" + i);
        }
    }
}
