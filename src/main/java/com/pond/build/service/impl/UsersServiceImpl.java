package com.pond.build.service.impl;

import com.pond.build.enums.HttpStatusCode;
import com.pond.build.model.LoginUser;
import com.pond.build.model.ResponseResult;
import com.pond.build.model.User;
import com.pond.build.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private LoginServiceImpl loginService;

    @Override
    public ResponseResult getMeInfo() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();
        List<String> permissions = loginUser.getPermissions();
        Map<String, Object> userInfoMap = loginService.putUserInfoToMap(userInfo.getId(), userInfo.getUserName(),
                userInfo.getNickName(), userInfo.getEmail(), userInfo.getAvatar(), userInfo.getPhoneNumber(), userInfo.getSex(), userInfo.getStatus(), permissions);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"获取成功",userInfoMap);

    }
}
