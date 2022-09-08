package com.release.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisApplicationTests {

    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("name","dadadingdada!");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

}
