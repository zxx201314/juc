package com.zhang.juc.threadpool;

import java.util.concurrent.*;

/**
 * 线程池三大方法，七大核心参数，4中拒绝策略
 */
public class Demo01 {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newSingleThreadExecutor(); //单个线程
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        ExecutorService executorService = Executors.newCachedThreadPool();
        //如何定义最大线程数
        //1.cpu密集型 多少线程就配置多少
        System.out.println(Runtime.getRuntime().availableProcessors());
        //2.IO密集型 >判断系统中IO耗时高的线程即可
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,//核心线程数
                6,//最大线程数
                0,//线程回收时间
                TimeUnit.SECONDS,//线程回收单位
                new LinkedBlockingDeque<>(5),//线程等待区
                Executors.defaultThreadFactory(),//默认的线程工厂
                new ThreadPoolExecutor.AbortPolicy());// 拒绝策略4种
        try {
            for (int i = 0; i < 13; i++) {
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }

    }
}
