package com.pond.build.controller;

import com.pond.build.model.ResponseResult;
import com.pond.build.model.Student;
import com.pond.build.model.User;
import com.pond.build.service.LoginService;
import com.pond.build.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;


    @GetMapping("/users/me")
    public ResponseResult getMeInfo(){
        return usersService.getMeInfo();
    }

    @GetMapping("/users")
    @PreAuthorize("permitAll")
    public ResponseResult getUsersByPage(@RequestParam(value = "page") Integer page, @RequestParam(value = "pageSize") Integer pageSize,
                                         @RequestParam(value = "searchText", defaultValue = "") String searchText,
                                         @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                         @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                         @RequestParam(value = "sort", defaultValue = "") String sort,
                                         @RequestParam(value = "order", defaultValue = "asc") String order){
        return usersService.getUsersByPage(page, pageSize, searchText, startDate, endDate, sort, order);
    }

//    http://localhost:5173/user/123
//    @GetMapping("/user/{id}")
//    public String getUserById(@PathVariable("id") Long userId) {
//        return "获取用户ID为" + userId + "的信息";
//    }

//    http://localhost:5173/user?name=John
//    @GetMapping("/user")
//    public String getUserByName(@RequestParam("name") String userName) {
//        return "获取用户名为" + userName + "的信息";
//    }




    @PostMapping("/users/{userId}/enable")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult setUserEnable(@PathVariable("userId") Integer userId){
        return usersService.setUserEnable(userId);
    }


    @PostMapping("/users/{userId}/disable")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult setUserDisable(@PathVariable("userId") Integer userId){
        return usersService.setUserDisable(userId);
    }

    @PatchMapping("/users/{userId}")
    @PreAuthorize("permitAll")
    public ResponseResult updateUserInfoByUserId(@PathVariable("userId") Integer userId, @RequestBody User user){
        return usersService.updateUserInfoByUserId(userId, user);
    }

    @PostMapping("/users/{userId}/change-password")
    @PreAuthorize("permitAll")
    public ResponseResult changePassword(@PathVariable("userId") Integer userId, @RequestBody String passWord){
        return usersService.changePassword(userId, passWord);
    }
}
