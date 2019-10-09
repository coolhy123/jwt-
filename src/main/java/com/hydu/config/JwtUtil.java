package com.hydu.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 * Created on 2019/10/9
 * @author heyong
 */

@ConfigurationProperties("jwt.config")
public class JwtUtil {
    public String key;
    public long time;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    /**
     * 创建token
     * @param id
     * @param subjcet
     * @param roles
     * @return
     */
    public String createJwt(String id,String subjcet,String roles){
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subjcet)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256,key)
                .claim("roles",roles);
        if(time>0){
            builder.setExpiration(new Date(nowMillis+time));
        }

        return builder.compact();
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public Claims praseJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}