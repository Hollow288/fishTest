package com.pond.build.handler;

import com.alibaba.fastjson.JSON;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.model.ResponseResult;
import com.pond.build.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证的异常处理类
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    //返回一个让前端使用/refreshToken/{refreshToken}接口刷新token的状态码

    //Todo 为什么在SecurityConfig中配置了以后(现已注释),全局的RuntimeException都会被捕获然后返回401,导致前端误以为需要刷新token
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatusCode.UNAUTHORIZED.getCode(),HttpStatusCode.UNAUTHORIZED.getCnMessage());
        String json = JSON.toJSONString(result);
        //处理移除
        WebUtils.renderString(response,json);
    }
}

