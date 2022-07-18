package com.zhang.juc.callable;

import java.util.concurrent.*;

public class CallAbleDemo {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        FutureTask futureTask = new FutureTask(myThread);
        new Thread(futureTask,"A").start();
        try {
            Object o = futureTask.get();
            System.out.println(o.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    
}
class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("Hello Call");
        return "CallAble";
    }
}
