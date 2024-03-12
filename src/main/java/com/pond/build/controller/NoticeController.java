package com.pond.build.controller;


import com.pond.build.model.ResponseResult;
import com.pond.build.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    @GetMapping("/notice")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult getNoticeListByPage(@RequestParam(value = "page") Integer page,
                                            @RequestParam(value = "pageSize") Integer pageSize,
                                            @RequestParam(value = "searchText", defaultValue = "") String searchText){
        return noticeService.getNoticeListByPage(page,pageSize,searchText);
    }


    @GetMapping("/notice/{noticeId}/all-userid-by-noticeid")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult allUserIdByNoticeId(@PathVariable("noticeId") String noticeId){
        return noticeService.allUserIdByNoticeId(noticeId);
    }
}
