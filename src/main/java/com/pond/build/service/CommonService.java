package com.pond.build.service;

import com.pond.build.model.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface CommonService {
    ResponseResult getAllItems(String fieldName);

    void doTask1();

    ResponseResult getAllRouterAndChildren();

    ResponseResult getAllMenuChildren();

    ResponseResult getAllPortalPortfolio(String type,Integer page,Integer pageSize);

    ResponseResult getAllPortalType();

    ResponseResult getAllNewsInformationPage(Integer page, Integer pageSize);

    ResponseResult getNewsInformationById(Integer newsId);

    ResponseResult addMessageBoard(Map<String, Object> map, HttpServletRequest request);
}
