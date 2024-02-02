package com.pond.build.controller;


import com.pond.build.model.ResponseResult;
import com.pond.build.service.UploadService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload/avatar")
    public ResponseResult uploadAvatar(MultipartFile file) {
        return uploadService.uploadAvatar(file);
    }
}
