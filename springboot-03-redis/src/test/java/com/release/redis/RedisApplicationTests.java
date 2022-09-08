package com.release.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.release.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
class RedisApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Test
    void testString() {
        //插入数据
        redisTemplate.opsForValue().set("name", "王五");
        //获取数据
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

    @Test
    void testSaveUser() {
        redisTemplate.opsForValue().set("user:2", new User("李四", 20));
        User user = (User) redisTemplate.opsForValue().get("user:2");
        System.out.println("user = " + user);
    }

    @Test
    void testDelUser() {
        Object andDelete = redisTemplate.opsForValue().getAndDelete("user:2");
        System.out.println("andDelete = " + andDelete);
    }

    /************************************************** StringRedisTemplate *******************************************/
    @Autowired(required = false)
    private StringRedisTemplate stringRedisTemplate;

    // JSON 工具
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testStringTemplate() throws JsonProcessingException {
        // 手动序列化
        String json = mapper.writeValueAsString(new User("李四", 20));
        // 写入数据
        stringRedisTemplate.opsForValue().set("user:2", json);
        // 获取数据
        String val = stringRedisTemplate.opsForValue().get("user:2");
        // 手动反序列化
        User user = mapper.readValue(val, User.class);
        System.out.println("user = " + user);
    }

    @Test
    void testHash() {
        stringRedisTemplate.opsForHash().put("user:3", "name", "赵六");
        stringRedisTemplate.opsForHash().put("user:3", "age", "20");
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:3");
        System.out.println("entries = " + entries);
    }
}
