package com.pond.build.controller;

import com.pond.build.model.Menu;
import com.pond.build.model.ResponseResult;
import com.pond.build.model.Role;
import com.pond.build.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;


    @GetMapping("/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult getRoleListByPage(@RequestParam(value = "page") Integer page,
                                            @RequestParam(value = "pageSize") Integer pageSize,
                                            @RequestParam(value = "searchText", defaultValue = "") String searchText){
        return roleService.getRoleListByPage(page,pageSize,searchText);
    }


    @PostMapping("/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult createRole(@RequestBody Role role){
        return roleService.createRole(role);
    }


    @PatchMapping("/role/{roleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult updateRoleById(@PathVariable("roleId") String roleId, @RequestBody Role role){
        return roleService.updateRoleById(roleId,role);
    }


    @PatchMapping("/role/delete-roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult deleteRolesByIds(@RequestBody Map<String,Object> roleIds){
        return roleService.deleteRolesByIds(roleIds);
    }

    @PostMapping("/role/{roleId}/add-users-role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult addUsersRole(@PathVariable String roleId,@RequestBody Map<String,Object> userIds){
        return roleService.addUsersRole(roleId,userIds);
    }


}
