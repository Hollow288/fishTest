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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            return new ResponseResult(HttpStatusCode.USERNAME_PASSWORD_ERR.getCode(),HttpStatusCode.USERNAME_PASSWORD_ERR.getCnMessage());
        }

        //如果认证没有通过，给出对应的提示
        if (Objects.isNull(authenticate)){
//            throw new RuntimeException("登录失败");
            return new ResponseResult(HttpStatusCode.USERNAME_PASSWORD_ERR.getCode(),HttpStatusCode.USERNAME_PASSWORD_ERR.getCnMessage());
        }

        //如果认证通过，使用user生成jwt  jwt存入ResponseResult 返回

        //如果认证通过，拿到这个当前登录用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        //获取当前用户的userid
        String userid = loginUser.getUser().getId().toString();

//        String jwt = JwtUtil.createJWT(userid);
        String accessToken = JwtUtil.createJWT(userid, JwtUtil.JWT_ACCESS_TTL);
        String refreshToken = JwtUtil.createJWT(userid, JwtUtil.JWT_REFRESH_TTL);

        Map<String, Object> map = new HashMap<>();
        map.put("access_token",accessToken);
        map.put("refresh_token",refreshToken);

        //前端需要的用户个人信息
        User userInfo = loginUser.getUser();
        List<String> permissions = loginUser.getPermissions();
        Map<String, Object> userInfoMap = this.putUserInfoToMap(userInfo.getId(), userInfo.getUserName(),
                userInfo.getNickName(), userInfo.getEmail(), userInfo.getAvatar(), userInfo.getPhoneNumber(), userInfo.getSex(), userInfo.getStatus(), permissions);

        map.put("user",JSONObject.toJSONString(userInfoMap));

        //把完整的用户信息存入redis  userid为key   用户信息为value 好像只有权限信息
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
            //2023.12.14 统一返回401的话,如果请求刷新token接口依然返回401,就返回到登录界面,也没什么问题

            //检查refreshToken是否过期
            Boolean tokenExpired = JwtUtil.isTokenExpired(refreshToken);
            if(tokenExpired){
                return new ResponseResult(HttpStatusCode.REFRESH_TOKEN_ERR.getCode(),HttpStatusCode.REFRESH_TOKEN_ERR.getCnMessage());
            }
            //检查refreshToken是否在黑名单
            if(redisUtil.isBlackToken(refreshToken)){
                return new ResponseResult(HttpStatusCode.REFRESH_TOKEN_ERR.getCode(),HttpStatusCode.REFRESH_TOKEN_ERR.getCnMessage());
            }

            Claims claims = JwtUtil.parseJWT(refreshToken);
            String userid = claims.getSubject();
            //检查refreshToken是否在redis中
            // (登出的时候，只是把两种token从redis中删除,当用户登出后,再次使用之前的AToken请求失败后,使用RToken刷新,这时候RToken既没有过期,也没有在黑名单,不就返回新的双token咯)
            if(!redisUtil.hasKey("refresh_token:"+ userid)){
                return new ResponseResult(HttpStatusCode.REFRESH_TOKEN_ERR.getCode(),HttpStatusCode.REFRESH_TOKEN_ERR.getCnMessage(),null);
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
            return new ResponseResult(HttpStatusCode.REFRESH_TOKEN_ERR.getCode(),HttpStatusCode.REFRESH_TOKEN_ERR.getCnMessage());
        }

    }

    //获取当前在线人数
    @Override
    public ResponseResult getOnlineNum() {
        //Todo 如果键的数量特别大，直接使用keys命令可能会导致性能问题，因为它需要遍历整个键空间。在这种情况下，可以考虑使用更为高效的方式来统计以特定前缀开头的键的数量。
        long numSpecificAboutKey = redisUtil.getNumSpecificAboutKey("access_token:");
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("onlineNum",numSpecificAboutKey);

        return new ResponseResult<>(HttpStatusCode.OK.getCode(),HttpStatusCode.OK.getCnMessage(),resultMap);
    }


    //返回给前端的用户信息
    public Map<String,Object> putUserInfoToMap(Long id, String userName, String nickName, String email, String avatar, String phoneNumber, String sex, String status, List<String> roles){
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("id", id);
        userInfoMap.put("userName", userName);
        userInfoMap.put("email", email);
        userInfoMap.put("phoneNumber", phoneNumber);
        userInfoMap.put("nickName", nickName);
        userInfoMap.put("avatarUrl", avatar);
        userInfoMap.put("gender", sex);
        if(Objects.equals(sex,"0")){
            userInfoMap.put("genderLabel", "女");
        }else {
            userInfoMap.put("genderLabel", "男");
        }
        if(Objects.equals(status,"0")){
            userInfoMap.put("enabled", true);
        }else {
            userInfoMap.put("enabled", false);
        }
        userInfoMap.put("roles", roles);
        return  userInfoMap;
    }


}
