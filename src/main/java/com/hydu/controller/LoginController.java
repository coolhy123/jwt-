package com.hydu.controller;

import com.hydu.config.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2019/10/9
 *
 * @author heyong
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    public JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @ResponseBody
    public String findAll(){
        return "访问成功";
    }


    /*** 用户登陆
     * @return
     * */
    @RequestMapping(value="/login",method= RequestMethod.POST)
    public Map login(@RequestBody Map<String,String> loginMap){
        boolean flag = false;
        if("admin".equals(loginMap.get("name")) && "123".equals(loginMap.get("password"))){
            flag = true;
        }
        //生成token
        Map map=new HashMap();
     if(flag){
        String token = jwtUtil.createJwt(loginMap.get("id"), loginMap.get("name"), "admin");

        map.put("token",token);
        map.put("name",loginMap.get("name"));
        map.put("code",200);
        map.put("msg","登录成功");
        //登陆名
        return map;
    }else{
         map.put("token",null);
         map.put("name",null);
         map.put("code",201);
         map.put("msg","登录失败");
        return map;
    }
}


    /***
     * 删除
     * @param id
     **/
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public Map<String,String> delete(@PathVariable String id ){
        Claims claims=(Claims) request.getAttribute("admin_claims");
        Map map=new HashMap();
        if(claims==null){
            map.put("code",500);
            map.put("msg","无权访问");
            return map;
        }
        map.put("code",200);
        map.put("msg","删除成功");
        return map;
    }
}