package com.pond.build.service;

import com.pond.build.model.ResponseResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

public interface UsersService {
    ResponseResult getMeInfo();

    ResponseResult getUsersByPage(Integer page, Integer pageSize, String searchText, Date startDate, Date endDate, String sort, String order);

    ResponseResult setUserEnable(Integer userId);

    ResponseResult setUserDisable(Integer userId);
}
