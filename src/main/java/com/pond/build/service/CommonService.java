package com.pond.build.service;

import com.pond.build.model.ResponseResult;

public interface CommonService {
    ResponseResult getAllItems(String fieldName);

    void doTask1();

    ResponseResult getAllRouterAndChildren();

    ResponseResult getAllMenuChildren();

    ResponseResult getAllPortalPortfolio(String type,Integer page,Integer pageSize);
}
