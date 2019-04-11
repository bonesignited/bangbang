package com.zlx.bangbang.utils;


import com.alibaba.fastjson.JSON;
import com.zlx.bangbang.constants.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Service
public class RedisUtil {

    // what is @Resource
    // @Resource 按名称 @Autowired 按类型
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public void storeNewCookie(String cookie, String userId) {
        redisTemplate.opsForValue().set(cookie, userId, Constant.TOKEN_EXPIRED, TimeUnit.SECONDS);
    }

    /**
     * 存储key，value，在expire分钟后过期
     */
    public void store(String key, String value, Integer expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.MINUTES);
    }

    /**
     * 存储key，value，在expire分钟后过期
     */
    public void store(String key, String value, Integer expire, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expire, timeUnit);
    }

    public void increment(String key, Double value) {
        redisTemplate.opsForValue().increment(key, value);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void store(String key,Object value, Integer expire){
        redisTemplate.opsForValue().set(key, JSON.toJSONString(value),expire,TimeUnit.MINUTES);
    }

    public Object getObj(String key,Class c){
        return JSON.parseObject(redisTemplate.opsForValue().get(key),c);
    }
}
