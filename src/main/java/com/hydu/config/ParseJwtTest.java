package com.hydu.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 2019/10/9
 * @author heyong
 * 解析tocken
 */
public class ParseJwtTest {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1NzA2MDMyNTksInJvbGVzIjoiYWRtaW4iLCJleHAiOjE1NzA2MDMzMTl9.Esr82pGG3wM68WKHdDMtmet-J_M0rKgjhhp9utyD_Do";
        Claims claims = Jwts.parser()
                .setSigningKey("coolhy")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("id:"+claims.getId());
        System.out.println("name:"+claims.getSubject());
        System.out.println(claims.get("roles"));
        System.out.println(claims.get("logo"));
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy‐MM‐dd hh:mm:ss");
        System.out.println("登录时间:"+sdf.format(claims.getIssuedAt()));
        System.out.println("过期时 间:"+sdf.format(claims.getExpiration()));
        System.out.println("当前时间:"+sdf.format(new Date()) );
    }
}