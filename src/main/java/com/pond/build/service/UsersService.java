package com.pond.build.service;

import com.pond.build.model.ResponseResult;
import com.pond.build.model.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

public interface UsersService {
    ResponseResult getMeInfo();

    ResponseResult getUsersByPage(Integer page, Integer pageSize, String searchText, Date startDate, Date endDate, String sort, String order);

    ResponseResult setUserEnable(Integer userId);

    ResponseResult setUserDisable(Integer userId);

    ResponseResult updateUserInfoByUserId(Integer userId,  User user);

    ResponseResult changePassword(Integer userId, Map<String,String> passWord);

    ResponseResult resetPassword(Integer userId, Map<String,String> passWord);
}
