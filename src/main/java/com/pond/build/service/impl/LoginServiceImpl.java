package com.pond.build.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.model.LoginUser;
import com.pond.build.model.ResponseResult;
import com.pond.build.model.User;
import com.pond.build.service.LoginService;
import com.pond.build.utils.JwtUtil;
import com.pond.build.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public ResponseResult login(User user) {

        //通过UsernamePasswordAuthenticationToken获取用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassWord());

        //AuthenticationManager委托机制对authenticationToken 进行用户认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //如果认证没有通过，给出对应的提示
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }

        //如果认证通过，使用user生成jwt  jwt存入ResponseResult 返回

        //如果认证通过，拿到这个当前登录用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        //获取当前用户的userid
        String userid = loginUser.getUser().getId().toString();

//        String jwt = JwtUtil.createJWT(userid);
        String accessToken = JwtUtil.createJWT(userid, JwtUtil.JWT_ACCESS_TTL);
        String refreshToken = JwtUtil.createJWT(userid, JwtUtil.JWT_REFRESH_TTL);

        Map<String, String> map = new HashMap<>();
        map.put("access_token",accessToken);
        map.put("refresh_token",refreshToken);

        //把完整的用户信息存入redis  userid为key   用户信息为value
//        redisUtil.set("login:"+userid, JSONObject.toJSONString(loginUser),3600);

        redisUtil.set("access_token:"+ userid,JSONObject.toJSONString(loginUser),JwtUtil.JWT_ACCESS_TTL/1000);
        redisUtil.set("refresh_token:"+ userid,JSONObject.toJSONString(loginUser),JwtUtil.JWT_REFRESH_TTL/1000);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"登录成功",map);
    }


    @Override
    public ResponseResult logout() {
        //从SecurityContextHolder中的userid
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();

        //根据userid找到redis对应值进行删除
        redisUtil.removeKey("access_token:"+userid);
        redisUtil.removeKey("refresh_token:"+userid);
        return new ResponseResult(HttpStatusCode.OK.getCode(),"注销成功");
    }

    @Override
    public ResponseResult refreshToken(String refreshToken) {

        try {

            //Todo 这里返回的状态码不应该和AuthenticationEntryPointImpl一样,因为这是refreshToken,如果也过期了,应该发一个让前端觉得应该返回登录界面的状态码

            //检查refreshToken是否过期
            Boolean tokenExpired = JwtUtil.isTokenExpired(refreshToken);
            if(tokenExpired){
                return new ResponseResult(HttpStatusCode.UNAUTHORIZED.getCode(),HttpStatusCode.UNAUTHORIZED.getCnMessage());
            }
            //检查refreshToken是否在黑名单
            if(redisUtil.isBlackToken(refreshToken)){
                return new ResponseResult(HttpStatusCode.UNAUTHORIZED.getCode(),HttpStatusCode.UNAUTHORIZED.getCnMessage());
            }

            Claims claims = JwtUtil.parseJWT(refreshToken);
            String userid = claims.getSubject();
            //检查refreshToken是否在redis中
            if(!redisUtil.hasKey("access_token:"+ userid)){
                return new ResponseResult(HttpStatusCode.UNAUTHORIZED.getCode(),HttpStatusCode.UNAUTHORIZED.getCnMessage());
            }

            Object userInfo = redisUtil.get("refresh_token:" + userid);
            //重置AccessToken和RefreshToken过期时间(秒)
            redisUtil.set("access_token:"+ userid,userInfo.toString(),JwtUtil.JWT_ACCESS_TTL/1000);
            redisUtil.set("refresh_token:"+ userid,userInfo.toString(),JwtUtil.JWT_REFRESH_TTL/1000);

            //生成新的AccessToken和RefreshToken
            Map<String, String> map = new HashMap<>();
            String newAccessToken = JwtUtil.createJWT(userid, JwtUtil.JWT_ACCESS_TTL);
            String newRefreshToken = JwtUtil.createJWT(userid, JwtUtil.JWT_REFRESH_TTL);
            //将refreshToken加入黑名单
            redisUtil.tokenAddToBlack(refreshToken);
            //将newAccessToken和newRefreshToken返回给前端
            map.put("access_token",newAccessToken);
            map.put("refresh_token",newRefreshToken);

            return new ResponseResult(HttpStatusCode.OK.getCode(),"刷新成功",map);

        } catch (Exception e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
            return new ResponseResult(HttpStatusCode.UNAUTHORIZED.getCode(),HttpStatusCode.UNAUTHORIZED.getCnMessage());
        }

    }


}
