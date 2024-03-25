package com.pond.build.service;

import com.pond.build.model.ResponseResult;

public interface CabinetService {
    ResponseResult getAllQuotation(Integer page, Integer pageSize,String searchText);
}
