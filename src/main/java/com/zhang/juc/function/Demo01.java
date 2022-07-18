package com.zhang.juc.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Demo01 {
    public static void main(String[] args) {
        //函数式接口，输入参数T，返回值R
        Function function = str -> str;
        System.out.println(function.apply("str"));
        //断定式接口，输入参数T，返回Boolen值
        Predicate<String> predicate = str -> str.isEmpty();
        System.out.println(predicate.test(""));
        //消费型接口
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("sssssssssss");
        //供给型接口
        Supplier supplier = () -> "test";
        supplier.get();
    }
}
