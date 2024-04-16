package com.pond.build.controller;

import com.pond.build.model.ResponseResult;
import com.pond.build.service.CommonService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.Map;


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

    @GetMapping("/common/portal-type")
    @PreAuthorize("permitAll")
    public ResponseResult getAllPortalType(){
        return commonService.getAllPortalType();
    }

    @GetMapping("/common/news-information-list")
    @PreAuthorize("permitAll")
    public ResponseResult getAllNewsInformationPage(@RequestParam(value = "page") Integer page,
                                           @RequestParam(value = "pageSize") Integer pageSize){
        return commonService.getAllNewsInformationPage(page,pageSize);
    }


    @GetMapping("/common/news-information")
    @PreAuthorize("permitAll")
    public ResponseResult getNewsInformationById(@RequestParam(value = "newsId") Integer newsId){
        return commonService.getNewsInformationById(newsId);
    }


    @PostMapping("/common/message-board")
    @PreAuthorize("permitAll")
    public ResponseResult addMessageBoard(@RequestBody Map<String,Object> map, HttpServletRequest request){
        return commonService.addMessageBoard(map,request);
    }
}
