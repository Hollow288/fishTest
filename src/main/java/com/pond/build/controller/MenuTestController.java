package com.pond.build.controller;


import com.pond.build.service.CommonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

@RestController
public class MenuTestController {

    private static final Logger logger = LogManager.getLogger(Example.class);


    @Autowired
    private CommonService commonService;

//    @PreAuthorize
//    注解的一些常用权限表达式：
//    "hasRole('ROLE_ADMIN')"：用户必须具有 "ROLE_ADMIN" 角色。
//    "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')"：用户必须具有 "ROLE_ADMIN" 或 "ROLE_USER" 中的任一角色。
//    "hasAuthority('READ_PRIVILEGE')"：用户必须具有 "READ_PRIVILEGE" 权限。
//    "hasAnyAuthority('READ_PRIVILEGE', 'WRITE_PRIVILEGE')"：用户必须具有 "READ_PRIVILEGE" 或 "WRITE_PRIVILEGE" 中的任一权限。
//    "isAuthenticated()"：用户必须已通过身份验证。
//    "permitAll"：所有用户都允许访问。
//
//    在LoginUser中我们将角色和权限都加入了authorities,其中ROLE_开头的就是角色,其他的是权限


//   如果未指定method属性，Spring MVC将处理所有请求方法，包括GET、POST、PUT、DELETE等。
    @GetMapping("/hello")
//    @PreAuthorize("hasAuthority('check')")
//    //自定义的权限功能
//    @PreAuthorize("@ex.hasAuthority('system:dept:list')")
//    @PreAuthorize("permitAll")
    public String hello(){
        long t1 = System.currentTimeMillis();
//        Thread.sleep(2000);
        commonService.doTask1();
        long t2 = System.currentTimeMillis();
        logger.info("task1方法耗时 {} ms" , t2-t1);
        return "hello";
    }
}
