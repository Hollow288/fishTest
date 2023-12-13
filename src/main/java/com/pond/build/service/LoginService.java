package com.pond.build.service;

import com.pond.build.model.ResponseResult;
import com.pond.build.model.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();

    ResponseResult refreshToken(String refreshToken);

}
