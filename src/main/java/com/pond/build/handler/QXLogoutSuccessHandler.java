package com.pond.build.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.model.LoginUser;
import com.pond.build.model.ResponseResult;
import com.pond.build.utils.JwtUtil;
import com.pond.build.utils.RedisUtil;
import com.pond.build.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 注销成功处理器
 */
@Component
public class QXLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private RedisUtil redisUtil;

    //Todo 为什么这里不行呢？配置了以后没有验证就提示验证失败(已处理 见SecurityConfig)
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            throw new RuntimeException("用户未登录");
        }
        //解析token
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从redis中获取用户信息
        String redisKey = "login:" + userid;
        Object result = redisUtil.get(redisKey);
        if(Objects.isNull(result)){
            throw new RuntimeException("用户未登录");
        }

        //根据userid找到redis对应值进行删除
        redisUtil.removeKey("login:"+userid);

        // 将结果写入响应
        ResponseResult returnResult = new ResponseResult(HttpStatusCode.OK.getCode(),"注销成功");
        String json = JSON.toJSONString(returnResult);

        //处理移除
        WebUtils.renderString(response,json);

    }
}

