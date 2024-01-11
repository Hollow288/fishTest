package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.UsersMapper;
import com.pond.build.model.LoginUser;
import com.pond.build.model.ResponseResult;
import com.pond.build.model.User;
import com.pond.build.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private LoginServiceImpl loginService;

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public ResponseResult getMeInfo() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();
        List<String> permissions = loginUser.getPermissions();
        Map<String, Object> userInfoMap = loginService.putUserInfoToMap(userInfo.getId(), userInfo.getUserName(),
                userInfo.getNickName(), userInfo.getEmail(), userInfo.getAvatar(), userInfo.getPhoneNumber(), userInfo.getSex(), userInfo.getStatus(), permissions);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"获取成功",userInfoMap);

    }

    @Override
    public ResponseResult getUsersByPage(Integer page, Integer pageSize, String searchText, Date startDate, Date endDate, String sort, String order) {
//        Page<User> pages = new Page<>(page, pageSize);
        // 假设页码从1开始
//        int page = 1; // 查询第一页
//        int pageSize = 10; // 每页10条数据
        int offset = (page - 1) * pageSize;
        int limit = pageSize;

        // 然后将 offset 和 limit 传递给 MyBatis 的查询语句
        List<User> users = usersMapper.getUsersByPage(searchText, startDate, endDate, offset, limit, sort, order);
        users.stream()
                .filter(Objects::nonNull) // 过滤掉可能为空的用户对象
                .forEach(user -> user.setPassWord(""));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data",users);
        resultMap.put("page",page);
        resultMap.put("pageSize",pageSize);
        Integer usersCountByPage = usersMapper.getUsersCountByPage(searchText, startDate, endDate);
        resultMap.put("total",usersCountByPage);
        return new ResponseResult(HttpStatusCode.OK.getCode(),"获取成功",resultMap);
    }

    @Override
    public ResponseResult setUserEnable(Integer id) {
        // 创建更新对象
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        // 设置更新条件，假设id为传入的参数
        updateWrapper.eq("id", id);
        // 设置要更新的字段和值
        updateWrapper.set("status", "0");

        // 执行更新操作
        boolean updateResult = usersMapper.update(null, updateWrapper) > 0;

        if (updateResult) {
            // 更新成功的处理逻辑
            return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
        } else {
            // 更新失败的处理逻辑
            return new ResponseResult(HttpStatusCode.REQUEST_SERVER_ERROR.getCode(),HttpStatusCode.REQUEST_SERVER_ERROR.getCnMessage());
        }
    }


    @Override
    public ResponseResult setUserDisable(Integer id) {
        // 创建更新对象
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        // 设置更新条件，假设id为传入的参数
        updateWrapper.eq("id", id);
        // 设置要更新的字段和值
        updateWrapper.set("status", "1");

        // 执行更新操作
        boolean updateResult = usersMapper.update(null, updateWrapper) > 0;

        if (updateResult) {
            // 更新成功的处理逻辑
            return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
        } else {
            // 更新失败的处理逻辑
            return new ResponseResult(HttpStatusCode.REQUEST_SERVER_ERROR.getCode(),HttpStatusCode.REQUEST_SERVER_ERROR.getCnMessage());
        }
    }
}
