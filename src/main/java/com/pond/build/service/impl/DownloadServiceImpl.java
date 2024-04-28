package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.AttachmentInformationMapper;
import com.pond.build.model.AttachmentInformation;
import com.pond.build.model.ResponseResult;
import com.pond.build.service.DownloadService;
import com.pond.build.utils.MinioUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Paths;

@Service
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    private MinioUtil minioUtil;

    @Autowired
    private AttachmentInformationMapper attachmentInformationMapper;


    @Override
    public ResponseEntity<byte[]> downloadQuotation(String attachId) {
        try {
            LambdaQueryWrapper<AttachmentInformation> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(AttachmentInformation::getAttachId,attachId);
            AttachmentInformation attachmentInformation = attachmentInformationMapper.selectOne(queryWrapper);
            String attachUrl = attachmentInformation.getAttachUrl();
            String[] split = attachUrl.split("/");
            String fileURL = "/"+split[split.length-2]+"/"+split[split.length-1];
            String fileName = split[split.length-1];
            InputStream is = minioUtil.getObject("fishtest-cabinet-quotation", fileURL);

            // 读取输入流的内容到字节数组中
            byte[] data = IOUtils.toByteArray(is);
            is.close();
            // 设置响应头，指定文件名和文件类型
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "UTF-8"));

            // 返回 ResponseEntity 对象，设置状态码为 200 OK
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<byte[]> downloadOrderStatus(String attachId) {
        try {
            LambdaQueryWrapper<AttachmentInformation> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(AttachmentInformation::getAttachId,attachId);
            AttachmentInformation attachmentInformation = attachmentInformationMapper.selectOne(queryWrapper);
            String attachUrl = attachmentInformation.getAttachUrl();
            String[] split = attachUrl.split("/");
            String fileURL = "/"+split[split.length-2]+"/"+split[split.length-1];
            String fileName = split[split.length-1];
            InputStream is = minioUtil.getObject("fishtest-cabinet-order-status", fileURL);

            // 读取输入流的内容到字节数组中
            byte[] data = IOUtils.toByteArray(is);
            is.close();
            // 设置响应头，指定文件名和文件类型
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "UTF-8"));

            // 返回 ResponseEntity 对象，设置状态码为 200 OK
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
