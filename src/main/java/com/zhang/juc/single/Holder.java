package com.zhang.juc.single;

/**
 * 静态内部类
 */
public class Holder {
    private Holder(){}

    public static Holder getInstance(){
       return Inner.holder;
    }
    public static class Inner{
        private static Holder holder = new Holder();
    }
}
