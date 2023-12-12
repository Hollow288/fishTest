package com.pond.build.handler;

import com.alibaba.fastjson.JSON;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.model.ResponseResult;
import com.pond.build.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证的异常处理类
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatusCode.UNAUTHORIZED.getCode(),HttpStatusCode.UNAUTHORIZED.getCnMessage());
        String json = JSON.toJSONString(result);
        //处理移除
        WebUtils.renderString(response,json);
    }
}

