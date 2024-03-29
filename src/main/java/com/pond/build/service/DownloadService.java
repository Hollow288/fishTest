package com.pond.build.service;

import com.pond.build.model.ResponseResult;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface DownloadService {
    ResponseEntity<byte[]> downloadQuotation(String attachId);
}
