package com.zhang.juc.single;

/**
 * 懒汉式，也是DCL模式
 */
public class LazyMan {
    private LazyMan() {

    }
    //volatile 保证可见性，不保证原子性，禁止指令重排，保证对象创建的安全性。
    private volatile static LazyMan lazyMan;

    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                lazyMan = new LazyMan();
            }
        }
        return lazyMan;
    }
}
class demo{
    LazyMan lazyMan = LazyMan.getInstance();
}