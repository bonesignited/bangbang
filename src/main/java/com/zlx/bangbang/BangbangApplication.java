package com.zlx.bangbang;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.cache.RedisCacheManager;

@SpringBootApplication
@Slf4j
public class BangbangApplication {

    public static void main(String[] args) {
        SpringApplication.run(BangbangApplication.class, args);
    }

}
