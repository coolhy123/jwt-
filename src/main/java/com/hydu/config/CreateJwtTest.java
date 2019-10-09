package com.hydu.config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Created on 2019/10/9
 * @author heyong
 * 创建tocken
 */
public class CreateJwtTest {
    public static void main(String[] args) {
        //当前时间
        long now = System.currentTimeMillis();
        //过期时间,1分钟
        long exp = now + 1000*60;
        JwtBuilder builder = Jwts.builder()
                //设置用户id
                .setId("9527")
                //设置用户姓名
                .setSubject("coolhy")
                //设置用户登录时间
                .setIssuedAt(new Date())
                //设置token头部
                .signWith(SignatureAlgorithm.HS256,"coolhy")
                .setExpiration(new Date(exp))
                .claim("roles","admin")
                .claim("logo","logo");
        System.out.println(builder.compact());

    }

}