package com.pond.build.handler;

import com.alibaba.fastjson.JSON;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.model.ResponseResult;
import com.pond.build.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 */
@Component
public class QXFailureHandler implements AuthenticationFailureHandler {

    //Todo 为什么这里不行呢？配置了以后没有验证就提示验证失败(已处理 见SecurityConfig)
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        System.out.println("认证失败");
        ResponseResult result = new ResponseResult(HttpStatusCode.UNAUTHORIZED.getCode(),HttpStatusCode.UNAUTHORIZED.getCnMessage());
        String json = JSON.toJSONString(result);
        //处理移除
        WebUtils.renderString(response,json);
    }
}

