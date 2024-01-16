package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.UsersMapper;
import com.pond.build.model.LoginUser;
import com.pond.build.model.Response.UserResponse;
import com.pond.build.model.ResponseResult;
import com.pond.build.model.User;
import com.pond.build.service.UsersService;
import org.springframework.beans.BeanUtils;
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
        Map<String, Object> userInfoMap = loginService.putUserInfoToMap(userInfo.getUserId(), userInfo.getUserName(),
                userInfo.getNickName(), userInfo.getEmail(), userInfo.getAvatarUrl(), userInfo.getPhoneNumber(), userInfo.getGender(), userInfo.getStatus(), permissions);

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
//        List<User> users = usersMapper.getUsersByPage(searchText, startDate, endDate, offset, limit, sort, order);
        List<UserResponse> userResponseList = usersMapper.getUsersByPage(searchText, startDate, endDate, offset, limit, sort, order);

//        List<UserResponse> userResponseList = new ArrayList<>();
//        for (User user : users) {
//            UserResponse userResponse = new UserResponse();
//            BeanUtils.copyProperties(user, userResponse);
//            // 这里可以添加额外的属性设置，例如 roles
//
////            userResponse.setRoles(strings);
//
//            userResponseList.add(userResponse);
//        }
//        userResponseList.stream().filter(Objects::nonNull) // 过滤掉可能为空的用户对象
//                .forEach(userResponse ->{
//                    if(Objects.equals(userResponse.getGender(),"0")){
//                        userResponse.setGenderLabel("女");
//                    }else if (Objects.equals(userResponse.getGender(),"1")){
//                        userResponse.setGenderLabel("男");
//                    }else {
//                        userResponse.setGenderLabel("未知");
//                    }
//                });

//        users.stream()
//                .filter(Objects::nonNull) // 过滤掉可能为空的用户对象
//                .forEach(user -> user.setPassWord(""));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data",userResponseList);
        resultMap.put("page",page);
        resultMap.put("pageSize",pageSize);
        Integer usersCountByPage = usersMapper.getUsersCountByPage(searchText, startDate, endDate);
        resultMap.put("total",usersCountByPage);
        return new ResponseResult(HttpStatusCode.OK.getCode(),"获取成功",resultMap);
    }

    @Override
    public ResponseResult setUserEnable(Integer userId) {
        // 创建更新对象
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        // 设置更新条件，假设id为传入的参数
        updateWrapper.eq("user_id", userId);
        // 设置要更新的字段和值
        updateWrapper.set("status", "0");

        // 执行更新操作
        boolean updateResult = usersMapper.update(null, updateWrapper) > 0;

        boolean recordResult = this.setUpdateByAndUpdateTime(userId);

        if (updateResult && recordResult) {
            // 更新成功的处理逻辑
            return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
        } else {
            // 更新失败的处理逻辑
            return new ResponseResult(HttpStatusCode.REQUEST_SERVER_ERROR.getCode(),HttpStatusCode.REQUEST_SERVER_ERROR.getCnMessage());
        }
    }


    @Override
    public ResponseResult setUserDisable(Integer userId) {
        // 创建更新对象
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        // 设置更新条件，假设id为传入的参数
        updateWrapper.eq("user_id", userId);
        // 设置要更新的字段和值
        updateWrapper.set("status", "1");

        // 执行更新操作
        boolean updateResult = usersMapper.update(null, updateWrapper) > 0;

        boolean recordResult = this.setUpdateByAndUpdateTime(userId);

        if (updateResult && recordResult) {
            // 更新成功的处理逻辑
            return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
        } else {
            // 更新失败的处理逻辑
            return new ResponseResult(HttpStatusCode.REQUEST_SERVER_ERROR.getCode(),HttpStatusCode.REQUEST_SERVER_ERROR.getCnMessage());
        }
    }

    @Override
    public ResponseResult updateUserInfoByUserId(Integer userId,  User user) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        User userInfo = loginUser.getUser();
        if(Objects.equals(userId,userInfo.getUserId()) && permissions.contains("ROLE_ADMIN")){
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_id", userId);

            updateWrapper.set("nick_name", user.getNickName());
            updateWrapper.set("email", user.getEmail());
            updateWrapper.set("city", user.getCity());
            updateWrapper.set("country", user.getCountry());
            updateWrapper.set("province", user.getProvince());
            updateWrapper.set("phone_number", user.getPhoneNumber());
            updateWrapper.set("gender", user.getGender());
            updateWrapper.set("birth_Date", user.getBirthDate());
            updateWrapper.set("address", user.getAddress());

            boolean updateResult = usersMapper.update(null, updateWrapper) > 0;
            boolean recordResult = this.setUpdateByAndUpdateTime(userId);

            return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");

        }else {
            return new ResponseResult(HttpStatusCode.FORBIDDEN_ROLE_ERR.getCode(),HttpStatusCode.FORBIDDEN_ROLE_ERR.getCnMessage());
        }
    }


    public boolean setUpdateByAndUpdateTime(Integer userId){
        //当前操作人
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", userId);

        updateWrapper.set("update_by",userInfo.getUserId());
        updateWrapper.set("update_time",new Date());

        return usersMapper.update(null, updateWrapper) > 0;
    }
}
