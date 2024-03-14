package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@Transactional
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
        lambdaQueryWrapper.eq(Menu::getVisible, "0");
//        lambdaQueryWrapper.isNotNull(Menu::getLabel);
        //不显示的就是路由,显示的就是菜单（visible来区分）  菜单的显示使用（status来区分）
        lambdaQueryWrapper.ne(Menu::getLabel, "");
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
        menuLambdaQueryWrapper.eq(Menu::getDelFlag,'0');
        menuLambdaQueryWrapper.orderBy(true, true, Menu::getSort); // true 表示升序，false 表示降序

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

    @Override
    public ResponseResult updateMenuById(long menuId, Menu menu) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        LambdaUpdateWrapper<Menu> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Menu::getMenuId,menu.getMenuId());
        updateWrapper.set(Menu::getKeyName,menu.getKeyName());
        updateWrapper.set(Menu::getTitle,menu.getTitle());
        updateWrapper.set(Menu::getLabel,menu.getLabel());
        updateWrapper.set(Menu::getPath,menu.getPath());
        updateWrapper.set(Menu::getComponent,menu.getComponent());
        updateWrapper.set(Menu::getVisible,menu.getVisible());
        updateWrapper.set(Menu::getStatus,menu.getStatus());
        updateWrapper.set(Menu::getIcon,menu.getIcon());
        updateWrapper.set(Menu::getUpdateBy,userInfo.getUserId());
        updateWrapper.set(Menu::getUpdateTime,new Date());
        updateWrapper.set(Menu::getRemark,menu.getRemark());
        updateWrapper.set(Menu::getDisableAuth,menu.getDisableAuth());
        updateWrapper.set(Menu::getDismissTab,menu.getDismissTab());
        updateWrapper.set(Menu::getRouterParentId,menu.getRouterParentId());
        updateWrapper.set(Menu::getMenuParentId,menu.getMenuParentId());
        updateWrapper.set(Menu::getIsLeaf,menu.getIsLeaf());

        boolean updateResult = menuMapper.update(null, updateWrapper) > 0;

        if(updateResult){
            return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
        }else {
            return new ResponseResult(HttpStatusCode.REQUEST_SERVER_ERROR.getCode(),HttpStatusCode.REQUEST_SERVER_ERROR.getCnMessage());
        }
    }

    @Override
    public ResponseResult createMenu(Menu menu) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        menu.setCreateBy(String.valueOf(userInfo.getUserId()));
        menu.setCreateTime(new Date());

        int insert = menuMapper.insert(menu);

        menuMapper.refreshIsLeaf();

        if(insert>0){
            return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
        }else {
            return new ResponseResult(HttpStatusCode.REQUEST_SERVER_ERROR.getCode(),HttpStatusCode.REQUEST_SERVER_ERROR.getCnMessage());
        }

    }

    @Override
    public ResponseResult deleteMenuById(long menuId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        LambdaUpdateWrapper<Menu> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Menu::getMenuId,menuId);
        updateWrapper.set(Menu::getDelFlag,"1");
        updateWrapper.set(Menu::getUpdateTime,new Date());
        updateWrapper.set(Menu::getUpdateBy,userInfo.getUserId());

        boolean updateResult = menuMapper.update(null, updateWrapper) > 0;

        menuMapper.refreshIsLeaf();

        if(updateResult){
            return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
        }else {
            return new ResponseResult(HttpStatusCode.REQUEST_SERVER_ERROR.getCode(),HttpStatusCode.REQUEST_SERVER_ERROR.getCnMessage());
        }
    }

    @Override
    public ResponseResult allMenuAneChildren() {
        List<Map<String, Object>> maps = menuMapper.selectMenuAndChildren();
        if(!CollectionUtils.isEmpty(maps)){
            return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功",maps);
        }else{
            return new ResponseResult(HttpStatusCode.REQUEST_SERVER_ERROR.getCode(),HttpStatusCode.REQUEST_SERVER_ERROR.getCnMessage());
        }
    }

    @Override
    public ResponseResult allMenuIdByRoleId(String roleId) {

        List<String> menuIds = menuMapper.allMenuIdByRoleId(roleId);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功",menuIds);
    }

    @Override
    public ResponseResult addMenuIdByRoleId(String roleId, Map<String, Object> menuIds) {
        menuMapper.deleteMenuIdByRoleId(roleId);

        List<String> ids = (List<String>)menuIds.get("menuIds");

        if(!CollectionUtils.isEmpty(ids)){
            menuMapper.insertMenuIdByRoleId(roleId,ids);
        }

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
    }
}
