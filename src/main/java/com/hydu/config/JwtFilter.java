package com.hydu.config;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2019/10/9
 * @author heyong
 */
@Component
public class JwtFilter extends HandlerInterceptorAdapter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler ) throws  Exception{
        System.out.println("经过了拦截器");
        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            final String token = authHeader.substring(7);
            Claims claims = jwtUtil.praseJwt(token);
            if (claims != null) {
                //如果是管理员
                if("admin".equals(claims.get("roles"))){
                    request.setAttribute("admin_claims", claims);
                    //如果是用户
                }if("user".equals(claims.get("roles"))){
                    request.setAttribute("user_claims", claims); }
            }
        }
        return true;
    }




    }