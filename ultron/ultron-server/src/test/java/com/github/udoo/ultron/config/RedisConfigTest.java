package com.github.udoo.ultron.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author dong.yang
 * @data 2019/7/19 11:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {

    @Autowired
    RedisTemplate<String, Object> stringObjectRedisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void testStringRedisTemplate() {
        stringRedisTemplate.opsForValue().set("hello", "hello redis");
        assertEquals("hello redis", stringRedisTemplate.opsForValue().get("hello"));
    }

    @Test
    public void testStringObjectRedisTemplate() {
        RedisEntry redisEntry = new RedisEntry("redis", "3");
        stringObjectRedisTemplate.opsForValue().set(redisEntry.getName(), redisEntry);
        assertEquals("3", ((RedisEntry) stringObjectRedisTemplate.opsForValue().get(redisEntry.getName())).getAge());
        //
        assertTrue(stringObjectRedisTemplate.delete(redisEntry.getName()));
    }
}
