package com.ego.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author liuweiwei
 * @since 2020-08-14
 */
//@Configuration
public class RedisConfig3 {
    /**
     * 因为在set后redis会序列化key和value是乱的，所以我们要在config中重写一下redisTemplate。
     */
    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisConfig3(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Helper class that simplifies Redis data access code.
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisSerializer<String> serializer = new StringRedisSerializer();
        RedisSerializer<Object> jackson = new GenericToStringSerializer<>(Object.class);
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);
        redisTemplate.setValueSerializer(jackson);
        redisTemplate.setHashValueSerializer(jackson);
        return redisTemplate;
    }
}
