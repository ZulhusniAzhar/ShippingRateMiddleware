package com.SPL_middleware.assignment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheService {
    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);
    private static final long CACHE_TTL = 3600; //1 hour

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Object getFromCache(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void saveToCache(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        //I didnt provide delete method as it will expire
        redisTemplate.expire(key, CACHE_TTL, TimeUnit.SECONDS);
        logger.info("Redis Cache will expire in an hour");
    }

}
