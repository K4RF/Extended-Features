package com.example.simplespringboot.controller;

import com.example.simplespringboot.dto.RedisRequestDto;
import com.example.simplespringboot.service.RedisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/redis/singleData")
public class RedisController {

    private final RedisService redisService;

    // 생성자 주입
    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    // 데이터 저장을 위한 POST 요청
    @PostMapping("/setValue")
    public ResponseEntity<String> setValue(@RequestBody RedisRequestDto requestDto) {
        redisService.setValue(requestDto.getKey(), requestDto.getValue());
        return ResponseEntity.ok("Value set successfully");
    }

    // 데이터 조회를 위한 GET 요청
    @GetMapping("/getValue/{key}")
    public ResponseEntity<String> getValue(@PathVariable String key) {
        String value = redisService.getValue(key);
        if (value == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(value);
    }

    // 데이터 삭제를 위한 DELETE 요청
    @DeleteMapping("/deleteValue/{key}")
    public ResponseEntity<String> deleteValue(@PathVariable String key) {
        redisService.deleteValue(key);
        return ResponseEntity.ok("Value deleted successfully");
    }
}