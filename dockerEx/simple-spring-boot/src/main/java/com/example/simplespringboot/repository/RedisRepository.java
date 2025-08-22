package com.example.simplespringboot.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final ValueOperations<String, String> valueOperations;

    public RedisRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue(); // String 타입 데이터를 다루기 위한 ValueOperations
    }

    public void setValue(String key, String value) {
        valueOperations.set(key, value);
    }

    public String getValue(String key) {
        return valueOperations.get(key);
    }

    public void deleteValue(String key) {
        redisTemplate.delete(key);
    }
}