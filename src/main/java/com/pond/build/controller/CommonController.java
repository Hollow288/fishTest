package com.pond.build.controller;

import com.pond.build.model.ResponseResult;
import com.pond.build.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CommonController {


    @Autowired
    private CommonService commonService;


    @GetMapping("/allItems")
    public ResponseResult getAllItems(@RequestParam String fieldName){
        return commonService.getAllItems(fieldName);
    }


    @GetMapping("/common/router-children")
    public ResponseResult getAllRouter(){
        return commonService.getAllRouterAndChildren();
    }


    @GetMapping("/common/menu-children")
    @PreAuthorize("permitAll")
    public ResponseResult getAllMenuChildren(){
        return commonService.getAllMenuChildren();
    }

    @GetMapping("/common/portal-portfolio")
    @PreAuthorize("permitAll")
    public ResponseResult getAllPortalPortfolio(
            @RequestParam(value = "type") String type,@RequestParam(value = "page") Integer page,
                                                @RequestParam(value = "pageSize") Integer pageSize){
        return commonService.getAllPortalPortfolio(type,page,pageSize);
    }
}
