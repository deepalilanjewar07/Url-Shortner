package com.example.urlshortner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimiterService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final int LIMIT = 10; // 10 requests per minute

    public boolean isAllowed(String key) {

        String redisKey = "rate:" + key;

        String value = redisTemplate.opsForValue().get(redisKey);

        if (value == null) {
            redisTemplate.opsForValue().set(redisKey, "1", Duration.ofMinutes(1));
            return true;
        }

        int count = Integer.parseInt(value);

        if (count >= LIMIT) {
            return false;
        }

        redisTemplate.opsForValue().increment(redisKey);
        return true;
    }
}