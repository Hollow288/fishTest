package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.RoleMapper;
import com.pond.build.model.*;
import com.pond.build.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public ResponseResult getRoleListByPage(Integer page, Integer pageSize, String searchText) {

        Page<Role> pages = new Page<>(page, pageSize);

        LambdaQueryWrapper<Role> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Role::getDelFlag, "0");
        if(!searchText.isBlank()){
            lambdaQueryWrapper.like(Role::getRoleName,searchText);
        }

        IPage<Role> menuPage = roleMapper.selectPage(pages, lambdaQueryWrapper);
        List<Role> roles = menuPage.getRecords();
        long total = menuPage.getTotal();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data",roles);
        resultMap.put("total",total);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"获取成功",resultMap);
    }

    @Override
    public ResponseResult createRole(Role role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        role.setCreateBy(userInfo.getUserId());
        role.setCreateTime(new Date());

        int insert = roleMapper.insert(role);

        if(insert>0){
            return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
        }else {
            return new ResponseResult(HttpStatusCode.REQUEST_SERVER_ERROR.getCode(),HttpStatusCode.REQUEST_SERVER_ERROR.getCnMessage());
        }
    }

    @Override
    public ResponseResult updateRoleById(String roleId, Role role) {


        QueryWrapper<Role> roleKeyQueryWrapper = new QueryWrapper<>();
        roleKeyQueryWrapper.eq("role_key",role.getRoleKey());
        roleKeyQueryWrapper.ne("role_id",roleId);
        List<Role> roleKeys = roleMapper.selectList(roleKeyQueryWrapper);
        if(!roleKeys.isEmpty()){
            return new ResponseResult(HttpStatusCode.REQUEST_SERVER_ERROR.getCode(),"该角色编码已经存在");
        }
        QueryWrapper<Role> roleNameQueryWrapper = new QueryWrapper<>();
        roleNameQueryWrapper.eq("role_name",role.getRoleName());
        roleNameQueryWrapper.ne("role_id",roleId);
        List<Role> roleNames = roleMapper.selectList(roleNameQueryWrapper);
        if(!roleNames.isEmpty()){
            return new ResponseResult(HttpStatusCode.REQUEST_SERVER_ERROR.getCode(),"该角色名称已经存在");
        }


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();


        LambdaUpdateWrapper<Role> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Role::getRoleId,roleId);
        updateWrapper.set(Role::getRoleKey,role.getRoleKey());
        updateWrapper.set(Role::getRoleName,role.getRoleName());
        updateWrapper.set(Role::getStatus,role.getStatus());
        updateWrapper.set(Role::getRemark,role.getRemark());
        updateWrapper.set(Role::getUpdateBy,userInfo.getUserId());
        updateWrapper.set(Role::getUpdateTime,new Date());

        boolean updateResult = roleMapper.update(null, updateWrapper) > 0;

        if(updateResult){
            return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
        }else {
            return new ResponseResult(HttpStatusCode.REQUEST_SERVER_ERROR.getCode(),HttpStatusCode.REQUEST_SERVER_ERROR.getCnMessage());
        }
    }

    @Override
    public ResponseResult deleteRolesByIds(Map<String,Object> roleIds) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        List<String> ids = (List<String>) roleIds.get("ids");

        Integer effectNum = roleMapper.deleteRolesByIds(ids, userInfo.getUserId(), new Date());

        if(effectNum > 0) {
            return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
        }else {
            return new ResponseResult(HttpStatusCode.REQUEST_SERVER_ERROR.getCode(),HttpStatusCode.REQUEST_SERVER_ERROR.getCnMessage());
        }


    }

    @Override
    public ResponseResult addUsersRole(String roleId, Map<String, Object> userIds) {

        roleMapper.deleteByRoleId(roleId);
        List<String> ids = (List<String>)userIds.get("userIds");
        if(!CollectionUtils.isEmpty(ids)){
            roleMapper.insertUsersRole(ids, roleId);
        }
        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
    }
}
