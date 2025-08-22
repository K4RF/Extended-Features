package com.example.simplespringboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisRequestDto {
    private String key;
    private String value;
}