package com.zhang.juc.lock8;

import java.util.concurrent.TimeUnit;

public class LockDemo {
    public static void main(String[] args) {
        phone phone = new phone();
        phone phone1 = new phone();

        new Thread(()->{
            phone.sendMsg();
        },"A").start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        new Thread(()->{
            phone1.call();
        },"B").start();
    }
}
class phone{
    public static synchronized void sendMsg(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public  static synchronized void call(){
        System.out.println("打电话");
    }
}
