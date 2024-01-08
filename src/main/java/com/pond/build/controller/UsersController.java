package com.pond.build.controller;

import com.pond.build.model.ResponseResult;
import com.pond.build.service.LoginService;
import com.pond.build.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
}
