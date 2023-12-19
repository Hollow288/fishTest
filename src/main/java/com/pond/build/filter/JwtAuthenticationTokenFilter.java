package com.pond.build.filter;

import com.alibaba.fastjson.JSONObject;
import com.pond.build.model.LoginUser;
import com.pond.build.model.Student;
import com.pond.build.utils.JwtUtil;
import com.pond.build.utils.RedisUtil;
import io.jsonwebtoken.Claims;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("access_token");
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
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
        String redisKey = "access_token:" + userid;
        Object result = redisUtil.get(redisKey);
        if(Objects.isNull(result)){
            throw new RuntimeException("用户未登录或AccessToken过期");
        }
        LoginUser loginUser = JSONObject.parseObject(result.toString(), LoginUser.class);
        //封装Authentication对象存入SecurityContextHolder

        // 获取权限信息封装到Authentication中
//        第一个参数：principal，通常是包含了用户信息的对象，如果包含权限信息，应该在这里传递。
//        第二个参数：credentials，通常是密码，或者在无密码认证场景下可以为 null。
//        第三个参数：authorities，表示用户的权限，如果包含权限信息，应该在这里传递。
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,loginUser.getPassword(),loginUser.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}

