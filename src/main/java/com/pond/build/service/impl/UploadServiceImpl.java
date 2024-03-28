package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.AttachmentInformationMapper;
import com.pond.build.mapper.CabinetMapper;
import com.pond.build.mapper.UsersMapper;
import com.pond.build.model.AttachmentInformation;
import com.pond.build.model.LoginUser;
import com.pond.build.model.ResponseResult;
import com.pond.build.model.User;
import com.pond.build.service.UploadService;
import com.pond.build.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class UploadServiceImpl  implements UploadService {

    @Autowired
    private MinioUtil minioUtil;

    @Value("${minio.endpoint}")
    private String address;
    //    改为指定桶的名字
//    @Value("${minio.bucketName}")
//    private String bucketName;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private AttachmentInformationMapper attachmentInformationMapper;

    @Override
    public ResponseResult uploadAvatar(MultipartFile file) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        String bucketName = "fishtest-avatar";
        String filePath = "/"+ LocalDate.now() + "/";

        minioUtil.existBucket(bucketName);

        //上传文件到Minio
        List<String> upload = minioUtil.upload(new MultipartFile[]{file}, bucketName, filePath);
        String path = address + "/" + bucketName + filePath + upload.get(0);
        //修改user表中的URL
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("user_id",userInfo.getUserId());
        userUpdateWrapper.set("avatar_Url",path);
        userUpdateWrapper.set("update_by",userInfo.getUserId());
        userUpdateWrapper.set("update_time",new Date());
        usersMapper.update(null,userUpdateWrapper);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("path",path);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功",resultMap);
    }

    @Override
    public ResponseResult uploadQuotation(MultipartFile[] files, String quotationId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        String bucketName = "fishtest-cabinet-quotation";
        String filePath = "/"+ LocalDate.now() + "/";

        minioUtil.existBucket(bucketName);
        //上传文件到Minio
        List<String> uploadNames = minioUtil.upload(files, bucketName, filePath);

        List<String> resultNames = uploadNames.stream().map(m -> address + "/" + bucketName + filePath + m).toList();


        for (String resultName : resultNames) {
            AttachmentInformation attachmentInformation = new AttachmentInformation();
            attachmentInformation.setCreateBy(userInfo.getUserId().toString());
            attachmentInformation.setCreateTime(new Date());
            attachmentInformation.setOriTableId(quotationId);
            attachmentInformation.setOriTableName("cabinet_quotation");
            attachmentInformation.setAttachUrl(resultName);
            attachmentInformationMapper.insert(attachmentInformation);
        }

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
    }

}
