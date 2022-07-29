package com.zhang.redis.phonecodedemo;

import com.sun.deploy.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.UUID;

@Slf4j
public class PhoneCodeDemo {
    public static void main(String[] args) {
        verifyCode("18061494706");
    }

    public static String getCodeFromRedis(String phone) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String codeKey = "verifyCode" + phone + ":code";
        String code = jedis.get(codeKey);
        jedis.close();
        return code;
    }

    public static void verifyCode(String phone) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String countKey = "verifyCode" + phone + ":count";
        String codeKey = "verifyCode" + phone + ":code";
        String count = jedis.get(countKey);
        if (count == null) {
            jedis.setex(countKey, 24 * 60 * 60, "1");
        } else if (Integer.parseInt(count) <= 2) {
            jedis.incr(countKey);
        } else if (Integer.parseInt(count) > 2) {
            log.error("今天已经发送了三次验证码");
            jedis.close();
            return;
        }
        String codeFromRedis = jedis.get(codeKey);
        jedis.setex(codeKey, 24 * 60 * 60, getPhoneCode());
        jedis.close();
        //次数key
    }

    //获取随机6位验证码
    public static String getPhoneCode() {
        String code = UUID.randomUUID().toString().substring(0, 6);
        return code;
    }
}
