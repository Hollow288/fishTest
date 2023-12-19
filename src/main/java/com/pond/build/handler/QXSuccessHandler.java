package com.pond.build.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.model.LoginUser;
import com.pond.build.model.ResponseResult;
import com.pond.build.utils.JwtUtil;
import com.pond.build.utils.RedisUtil;
import com.pond.build.utils.WebUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 认证成功处理器
 */
@Component
public class QXSuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
    private RedisUtil redisUtil;

    //Todo 为什么这里不行呢？配置了以后没有验证就提示验证失败 (已处理 见SecurityConfig)
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        //获取当前用户的userid
        String userid = loginUser.getUser().getId().toString();

        String jwt = JwtUtil.createJWT(userid);
        Map<String, String> map = new HashMap<>();
        map.put("token",jwt);

        //把完整的用户信息存入redis  userid为key   用户信息为value
        redisUtil.set("login:"+userid, JSONObject.toJSONString(loginUser),3600);

        // 将结果写入响应
        ResponseResult result = new ResponseResult(HttpStatusCode.OK.getCode(),HttpStatusCode.OK.getCnMessage(),map);
        String json = JSON.toJSONString(result);

        //处理移除
        WebUtils.renderString(response,json);

    }
}

