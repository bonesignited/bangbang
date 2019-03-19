package com.zlx.bangbang.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * Token 机制及其刷新
 * 客户端第一次登录时，服务端进行用户名和密码的认证，成功的话就返回 token，过期时间设置为 t，存入redis（以用户id为key），
 * 并设置 redis 中的过期时间是 2 * t
 * 客户端以后登录都带着 token，当其发来过期的 token 时，只要 redis 中仍存在该 token，
 * 就返回一个新的token，并允许此次访问且不要求客户端重新登录
 */
@Component
@Slf4j
public class TokenUtil {
    @Value("${token.header}")
    private String tokenHeader;

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expiration}")
    private Long expiration;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 根据 claims 生成 Token
     *
     * @param userId
     * @return
     */
    public String generateToken(String userId) {
        Date exp = new Date(System.currentTimeMillis() + this.expiration * 1000);
        String token = Jwts.builder()
                .setSubject(userId)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, this.secret.getBytes(StandardCharsets.UTF_8))
                .compact();
        redisTemplate.opsForValue().set(userId, token, this.expiration, TimeUnit.SECONDS);
        return token;
    }

    /**
     * 检查 token 是否处于有效期内，如果超过 2 * t，就不从 redis 里取数据了，直接要求客户端重新登录
     * 若没有，从 token 里拿到 id，与 redis 里的比对，若正确且超过 t，允许此次访问并颁发新 token，更新 redis
     *
     * @param token
     * @return
     */
    public String validateToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
        String userId = claims.getSubject();
        Date exp = claims.getExpiration();
        Date now = new Date(System.currentTimeMillis());

        if (redisTemplate.hasKey(userId)) {
            if (now.before(exp)) {  // 未过期
                String oldToken = redisTemplate.opsForValue().get(userId);
                log.info(userId + " 的 token 没有过期，查到后直接返回");
                return oldToken;
            } else if (now.after(exp) || now.before(new Date(exp.getTime() + this.expiration))) {
                String refreshedToken = this.generateToken(userId);
                // 设置新的token及其过期时间
                redisTemplate.opsForValue().set(userId, refreshedToken, this.expiration, TimeUnit.SECONDS);
                log.info(userId + " 的 token 需要刷新");
                return refreshedToken;
            } else {
                // 完全过期，需要重新生成
                log.info(userId + " 的 token 已过期，需要重新登录");
                return null;
            }
        } else {
            // 完全过期，需要重新生成
            log.info(userId + " 的 token 已过期，需要重新登录");
            return null;
        }
    }
}
