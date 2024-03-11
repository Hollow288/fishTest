package com.pond.build.service;

import com.pond.build.model.Menu;
import com.pond.build.model.ResponseResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface MenuService {
    ResponseResult getAllMenu(Integer page, Integer pageSize);

    ResponseResult getOnlyMenu(Integer page, Integer pageSize);

    ResponseResult menuByParentId(long menuId);

    ResponseResult reviseMenuSortByMenuId(long menuId, Integer sort);

    ResponseResult updateMenuById(long menuId, Menu menu);

    ResponseResult createMenu(Menu menu);

    ResponseResult deleteMenuById(long menuId);

    ResponseResult allMenuAneChildren();

    ResponseResult allMenuIdByRoleId(String roleId);

    ResponseResult addMenuIdByRoleId(String roleId, Map<String, Object> menuIds);
}
