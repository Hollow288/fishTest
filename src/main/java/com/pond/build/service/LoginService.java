package com.pond.build.service;

import com.pond.build.model.ResponseResult;
import com.pond.build.model.User;

import java.util.Map;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();

    ResponseResult refreshToken(String refreshToken);

    ResponseResult getOnlineNum();

    ResponseResult hasRoleToInterface(String userId, Map<String,Object> map);
}
