package com.example.simplespringboot.controller;

import com.example.simplespringboot.dto.RedisRequestDto;
import com.example.simplespringboot.service.RedisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/redis/singleData")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    /**
     * 반환 타입을 ResponseEntity<Integer>로 변경합니다.
     * 서비스 계층에서 받은 결과(1)를 body에 담아 응답합니다.
     */
    @PostMapping("/setValue")
    public ResponseEntity<Integer> setValue(@RequestBody RedisRequestDto requestDto) {
        int result = redisService.setValue(requestDto.getKey(), requestDto.getValue());
        return ResponseEntity.ok(result);
    }

    // ... (다른 엔드포인트는 그대로)
    @GetMapping("/getValue/{key}")
    public ResponseEntity<String> getValue(@PathVariable String key) {
        String value = redisService.getValue(key);
        if (value == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(value);
    }
}