package com.pond.build.service;

import com.pond.build.model.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    ResponseResult uploadAvatar(MultipartFile file);
}
