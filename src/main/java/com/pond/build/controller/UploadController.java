package com.pond.build.controller;


import com.pond.build.model.ResponseResult;
import com.pond.build.service.UploadService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;


@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload/avatar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult uploadAvatar(MultipartFile file) {
        return uploadService.uploadAvatar(file);
    }


    @PostMapping("/upload/{quotationId}/quotation")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult uploadQuotation(@RequestParam(value = "file") MultipartFile[] files,
                                          @PathVariable("quotationId") String quotationId) {
        return uploadService.uploadQuotation(files,quotationId);
    }


    @PostMapping("/upload/{folioId}/portfolio-thumbnail")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult uploadPortFolioThumbnailFile(@RequestParam(value = "file") MultipartFile[] files,
                                          @PathVariable("folioId") String folioId) {
        return uploadService.uploadPortFolioThumbnailFile(files,folioId);
    }

    @PostMapping("/upload/{folioId}/portfolio-panorama")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult uploadPortFolioPanoramaFile(@RequestParam(value = "file") MultipartFile[] files,
                                                       @PathVariable("folioId") String folioId) {
        return uploadService.uploadPortFolioPanoramaFile(files,folioId);
    }


    @PostMapping("/upload/{newsId}/news-information")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult uploadNewsInformationFile(@RequestParam(value = "file") MultipartFile[] files,
                                                      @PathVariable("newsId") String newsId) {
        return uploadService.uploadNewsInformationFile(files,newsId);
    }


    @PostMapping("/upload/{orderId}/order-status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult uploadOrderStatusFile(@RequestParam(value = "file") MultipartFile[] files,
                                                    @PathVariable("orderId") String orderId) {
        return uploadService.uploadOrderStatusFile(files,orderId);
    }

}
