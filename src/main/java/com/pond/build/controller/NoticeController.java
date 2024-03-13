package com.pond.build.controller;


import com.pond.build.model.ResponseResult;
import com.pond.build.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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


    @PostMapping("/notice")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult createNotice(@RequestBody HashMap<String,Object> notices){
        return noticeService.createNotice(notices);
    }


    @PatchMapping("/notice/delete-notices")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult deleteNoticeByIds(@RequestBody HashMap<String,Object> noticeIds){
        return noticeService.deleteNoticeByIds(noticeIds);
    }


    @PatchMapping("/notice/{noticeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult updateNoticeById(@PathVariable("noticeId") String noticeId, @RequestBody HashMap<String,Object> notice){
        return noticeService.updateNoticeById(noticeId,notice);
    }


    @PutMapping("/notice/{noticeId}/update-publish-notice")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult updateAndPublishNotice(@PathVariable("noticeId") String noticeId, @RequestBody HashMap<String,Object> notice){
        return noticeService.updateAndPublishNotice(noticeId,notice);
    }


    @PostMapping("/notice/create-publish-notice")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult createAndPublishNotice(@RequestBody HashMap<String,Object> notice){
        return noticeService.createAndPublishNotice(notice);
    }


    @GetMapping("/notice/{userId}/notice-me")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult noticesByUserId(@PathVariable String userId){
        return noticeService.noticesByUserId(userId);
    }
}
