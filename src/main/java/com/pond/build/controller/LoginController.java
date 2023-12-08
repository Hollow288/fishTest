package com.pond.build.controller;

import com.pond.build.model.ResponseResult;
import com.pond.build.model.User;
import com.pond.build.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    @PreAuthorize("permitAll")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }

    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();

    }

}