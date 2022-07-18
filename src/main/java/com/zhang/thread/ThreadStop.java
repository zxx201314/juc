package com.zhang.thread;

/**
 * 测试停止线程
 * 1、建议线程正常停止------>利用次数，不建议死循环
 * 2、建议使用外部标志位----->设置一个标志位
 * 3、不要使用stop或者jdk已经废弃的方法终止线程
 */
public class ThreadStop implements Runnable {

    boolean flag = true;

    @Override
    public void run() {
        int j = 0;
        while (flag) {
            System.out.println("run........ thread" + j++);
        }
    }

    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) {

        ThreadStop threadStop = new ThreadStop();

        new Thread(threadStop).start();

        for (int i = 0; i < 100000; i++) {
            if (i == 900) {
                System.out.println(i);
                //调用stop方法，切换标志位，让线程停止
                threadStop.stop();
                System.out.println("线程停止了");
            }
        }
    }
}
