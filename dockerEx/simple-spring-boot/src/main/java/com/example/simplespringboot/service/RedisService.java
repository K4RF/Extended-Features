package com.example.simplespringboot.service;

import com.example.simplespringboot.repository.RedisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) // 기본적으로는 읽기 전용으로 설정
public class RedisService {

    private final RedisRepository redisRepository;

    public RedisService(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    // 데이터를 저장하는 로직
    @Transactional // 쓰기 작업이므로 트랜잭션 어노테이션 추가
    public void setValue(String key, String value) {
        redisRepository.setValue(key, value);
    }

    // 데이터를 조회하는 로직
    public String getValue(String key) {
        return redisRepository.getValue(key);
    }


    // 데이터를 삭제하는 로직
    @Transactional
    public void deleteValue(String key) {
        redisRepository.deleteValue(key);
    }
}