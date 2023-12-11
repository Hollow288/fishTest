package com.pond.build.controller;

import com.pond.build.model.ResponseResult;
import com.pond.build.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
