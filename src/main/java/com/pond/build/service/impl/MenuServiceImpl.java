package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.MenuMapper;
import com.pond.build.model.LoginUser;
import com.pond.build.model.Menu;
import com.pond.build.model.ResponseResult;
import com.pond.build.model.User;
import com.pond.build.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public ResponseResult getAllMenu(Integer page, Integer pageSize) {

        Page<Menu> pages = new Page<>(page, pageSize);


        LambdaQueryWrapper<Menu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Menu::getDelFlag, "0");
        lambdaQueryWrapper.eq(Menu::getMenuParentId, "0");
        lambdaQueryWrapper.orderByAsc(Menu::getSort);

//        List<Menu> menus = menuMapper.selectList(lambdaQueryWrapper);
        IPage<Menu> menuPage = menuMapper.selectPage(pages, lambdaQueryWrapper);
        List<Menu> menus = menuPage.getRecords();
        long total = menuPage.getTotal();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data",menus);
        resultMap.put("total",total);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"获取成功",resultMap);

    }


    @Override
    public ResponseResult getOnlyMenu(Integer page, Integer pageSize) {

        Page<Menu> pages = new Page<>(page, pageSize);


        LambdaQueryWrapper<Menu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Menu::getDelFlag, "0");
        lambdaQueryWrapper.eq(Menu::getMenuParentId, "0");
        lambdaQueryWrapper.isNotNull(Menu::getLabel);
        lambdaQueryWrapper.orderByAsc(Menu::getSort);

//        List<Menu> menus = menuMapper.selectList(lambdaQueryWrapper);
        IPage<Menu> menuPage = menuMapper.selectPage(pages, lambdaQueryWrapper);
        List<Menu> menus = menuPage.getRecords();
        long total = menuPage.getTotal();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data",menus);
        resultMap.put("total",total);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"获取成功",resultMap);

    }

    @Override
    public ResponseResult menuByParentId(long menuId) {
        LambdaQueryWrapper<Menu> menuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        menuLambdaQueryWrapper.eq(Menu::getMenuParentId,menuId);

        List<Menu> menus = menuMapper.selectList(menuLambdaQueryWrapper);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"获取成功",menus);

    }

    @Override
    public ResponseResult reviseMenuSortByMenuId(long menuId, Integer sort) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        UpdateWrapper<Menu> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("menu_id",menuId);
        updateWrapper.set("sort",sort);
        updateWrapper.set("update_by",userInfo.getUserId());
        updateWrapper.set("update_time",new Date());

        boolean updateResult = menuMapper.update(null, updateWrapper) > 0;

        if(updateResult){
            return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
        }else {
            return new ResponseResult(HttpStatusCode.REQUEST_SERVER_ERROR.getCode(),HttpStatusCode.REQUEST_SERVER_ERROR.getCnMessage());
        }
    }
}
