package com.zhang.juc.unsafe;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
    public static void main(String[] args) {
        List list = new CopyOnWriteArrayList();
        list.forEach((u)-> System.out.println(u));
    }
}
