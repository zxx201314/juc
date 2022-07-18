package com.zhang.juc.stream;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test {
    //题目要求
    //只用一行代码实现！
    // 1.ID必须是偶数
    // 2.年龄大于12岁
    // 3.用户名转换为大写字母
    // 4.用户名字母倒序
    // 5.只输出一个用户
    public static void main(String[] args) {
        User user = new User(1, "a", 10);
        User user1 = new User(2, "b", 12);
        User user2 = new User(3, "c", 13);
        User user3 = new User(4, "d", 14);
        User user4 = new User(5, "e", 15);
        User user5 = new User(6, "f", 16);

        List<User> users = Arrays.asList(user, user1, user2, user3, user4, user5);
        users.stream()
                .filter(u -> u.getId() % 2 == 0)
                .filter(u -> u.getAge() > 12)
                .map(u -> u.getName().toUpperCase())
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .forEach(System.out::println);
    }
}
