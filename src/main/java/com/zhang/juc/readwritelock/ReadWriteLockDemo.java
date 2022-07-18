package com.zhang.juc.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        Mycache mycache = new Mycache();
        for (int i = 0; i < 50; i++) {
            final int temp = i;
            new Thread(()->{
                mycache.put(temp+"",temp);
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 50; i++) {
            final int temp = i;
            new Thread(()->{
                mycache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }

}
class Mycache{
    private volatile Map<String,Object> map =new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public void put(String key,Object object){
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"写入："+key);
        map.put(key,object);
        System.out.println(Thread.currentThread().getName()+"写入成功");
        readWriteLock.writeLock().unlock();
    }
    public void get(String key){
        readWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"获取："+key);
        map.get(key);
        System.out.println(Thread.currentThread().getName()+"获取成功");
        readWriteLock.readLock().unlock();
    }
}
