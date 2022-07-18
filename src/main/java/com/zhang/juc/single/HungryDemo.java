package com.zhang.juc.single;

/**
 * 饿汉式：会导致内存空间浪费
 */
public class HungryDemo {

    private HungryDemo(){}

    private final static HungryDemo hungryDemo = new HungryDemo();

    public static HungryDemo getInstance(){
        return hungryDemo;
    }
}
