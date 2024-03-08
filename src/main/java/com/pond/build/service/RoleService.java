package com.pond.build.service;

import com.pond.build.model.ResponseResult;
import com.pond.build.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    ResponseResult getRoleListByPage(Integer page, Integer pageSize, String searchText);

    ResponseResult createRole(Role role);

    ResponseResult updateRoleById(String roleId, Role role);

    ResponseResult deleteRolesByIds(Map<String,Object> roleIds);

    ResponseResult addUsersRole(String roleId, Map<String, Object> userIds);
}
