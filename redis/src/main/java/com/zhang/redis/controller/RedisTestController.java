package com.zhang.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("/redisTestController")
@Slf4j
public class RedisTestController {
    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/setToRedis")
    public String setToRedis() {
        String code = "abc";
        redisTemplate.opsForValue().set("k1", code);
        return code;
    }

    @GetMapping("/getFromRedis")
    public String getFromRedis() {
        Object k1 = redisTemplate.opsForValue().get("k1");
        return k1.toString();
    }
}
