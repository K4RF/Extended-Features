package com.example.simplespringboot.service;

import com.example.simplespringboot.repository.RedisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedisService {

    private final RedisRepository redisRepository;

    public RedisService(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    /**
     * 반환 타입을 int로 변경하고, 성공 시 1을 반환합니다.
     */
    @Transactional
    public int setValue(String key, String value) {
        redisRepository.setValue(key, value);
        // 예외 없이 성공적으로 실행되면 1을 반환
        return 1;
    }

    // ... (getValue, deleteValue 메서드는 그대로)
    public String getValue(String key) {
        return redisRepository.getValue(key);
    }

    @Transactional
    public void deleteValue(String key) {
        redisRepository.deleteValue(key);
    }
}