package com.pond.build.service;

import com.pond.build.model.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface UploadService {
    ResponseResult uploadAvatar(MultipartFile file);

    ResponseResult uploadQuotation(MultipartFile[] files, String quotationId);

    ResponseResult uploadPortFolioThumbnailFile(MultipartFile[] files, String folioId);

    ResponseResult uploadPortFolioPanoramaFile(MultipartFile[] files, String folioId);
}
