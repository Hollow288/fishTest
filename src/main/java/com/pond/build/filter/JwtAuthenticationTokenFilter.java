package com.pond.build.filter;

import com.alibaba.fastjson.JSONObject;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.model.LoginUser;
import com.pond.build.model.ResponseResult;
import com.pond.build.model.Student;
import com.pond.build.utils.JwtUtil;
import com.pond.build.utils.RedisUtil;
import io.jsonwebtoken.Claims;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${pond.prefix}")
    private String tokenPrefix;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //只有登录接口和刷新token接口放行
        String requestURI = request.getRequestURI();

        if(requestURI.equals(contextPath + "/user/login") && "POST".equalsIgnoreCase(request.getMethod()) ||
                requestURI.equals(contextPath + "/user/refresh") && "POST".equalsIgnoreCase(request.getMethod())||
                requestURI.equals(contextPath + "/common/router-children")||
                requestURI.equals(contextPath + "/common/portal-portfolio")||
                requestURI.equals(contextPath + "/common/portal-type")||
                requestURI.equals(contextPath + "/common/news-information-list")||
                requestURI.equals(contextPath + "/common/news-information")||
                requestURI.startsWith(contextPath + "/fishTest") ||
                requestURI.startsWith(contextPath + "/sse")){
            //放行
            filterChain.doFilter(request, response);
            return;
        }

        //获取token
        String token = request.getHeader("Authorization").substring(tokenPrefix.length() + 1);
        if (!StringUtils.hasText(token)) {
            //放行
//            filterChain.doFilter(request, response);
//            return;
//            throw new RuntimeException("未检测到token");
            this.returnTokenError(response);
            return;
        }
        //解析token
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
//            throw new RuntimeException("token非法");
            this.returnTokenError(response);
            return;
        }
        //从redis中获取用户信息
        String redisKey = "access_token:" + userid;
        Object result = redisUtil.get(redisKey);
        if(Objects.isNull(result)){
//            throw new RuntimeException("用户未登录或AccessToken过期");
            this.returnTokenError(response);
            return;
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


    public void  returnTokenError(HttpServletResponse response) throws IOException {
        ResponseResult result = new ResponseResult(HttpStatusCode.UNAUTHORIZED.getCode(), HttpStatusCode.UNAUTHORIZED.getCnMessage());
        // Todo 这里没有弄明白,为什么这里不给data赋值,返回给前端的响应体就是 {}
        result.setData(new JSONObject());  // 设置为空的 JSONObject
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONObject.toJSONString(result));
    }
}

