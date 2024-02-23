package com.pond.build.service;

import com.pond.build.model.ResponseResult;
import org.springframework.web.bind.annotation.RequestParam;

public interface MenuService {
    ResponseResult getAllMenu(Integer page, Integer pageSize);

    ResponseResult menuByParentId(long menuId);
}
