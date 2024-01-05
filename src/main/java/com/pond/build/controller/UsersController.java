package com.pond.build.controller;

import com.pond.build.model.ResponseResult;
import com.pond.build.service.LoginService;
import com.pond.build.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;


    @GetMapping("/users/me")
    public ResponseResult getMeInfo(){
        return usersService.getMeInfo();
    }
}
