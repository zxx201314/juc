package com.zhang.jmm.learncollection;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
//java.util.ConcurrentModificationException 通用异常
@Slf4j
public class ContainerNotSafeDemo {

    public static void main(String[] args) {

    }

    private static void safeMap() {
        Map<String,String> map = new ConcurrentHashMap<>(); //Collections.synchronizedMap(new HashMap<>());//new HashMap<>();
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(()->{
                map.put(String.valueOf(finalI),UUID.randomUUID().toString().substring(0,4));
                log.info(map.toString());
            },String.valueOf(i)).start();
        }
    }

    private static void safeList() {
        List<Object> arrayList = new CopyOnWriteArrayList<>();  //Collections.synchronizedList(new ArrayList<>());  //new ArrayList();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                arrayList.add(UUID.randomUUID().toString().substring(0,4));
                log.info(arrayList.toString());
            },String.valueOf(i)).start();
        }
    }

    private static void safeSet() {
        Set<Integer> set = Collections.synchronizedSet(new HashSet<>());    //new CopyOnWriteArraySet<>();      // new HashSet();
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(()->{
                set.add(finalI);
                System.out.println(set);
            }).start();
        }
    }
}
