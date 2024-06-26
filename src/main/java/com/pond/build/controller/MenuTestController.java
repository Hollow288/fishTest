package com.pond.build.controller;


import com.pond.build.mapper.CommonMapper;
import com.pond.build.mapper.MenuMapper;
import com.pond.build.mapper.SseMapper;
import com.pond.build.model.Response.NoticeResponse;
import com.pond.build.service.CommonService;
import com.pond.build.service.ExportService;
import com.pond.build.service.impl.ExportServiceImpl;
import com.pond.build.utils.MinioUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(("/fishTest"))
public class MenuTestController {

    private static final Logger logger = LogManager.getLogger(Example.class);


    @Autowired
    private CommonService commonService;


    @Autowired
    private MenuMapper menuMapper;


    @Autowired
    private SseMapper sseMapper;


    @Autowired
    private CommonMapper commonMapper;


    @Autowired
    private ExportService exportService;


    @GetMapping("/test1")
    public void fishTest(){
//        List<Map<String, Object>> maps = menuMapper.selectMenuAndChildren();
//        List<NoticeResponse> noticeResponses = sseMapper.getAllNeedPendingNotice();
//        List<Map<String, Object>> allRouterAndChildren = commonMapper.getAllRouterAndChildren();
//        List<String> menuIds = commonMapper.getAllMenuIdByUserId("1");
//
//        List<String> strings = commonMapper.selectMenuParentId(menuIds);
//
//        List<String> mergedList = Stream.concat(menuIds.stream(), strings.stream())
//                .distinct() // 去重
//                .filter(id -> !id.equals("0")) // 过滤掉值为"0"的元素
//                .collect(Collectors.toList());
        exportService.exportQuotation("1");

//        System.out.println(menuIds);
    }

//    @PreAuthorize
//    注解的一些常用权限表达式：
//    "hasRole('ROLE_ADMIN')"：用户必须具有 "ROLE_ADMIN" 角色。
//    "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')"：用户必须具有 "ROLE_ADMIN" 或 "ROLE_USER" 中的任一角色。
//    "hasAuthority('READ_PRIVILEGE')"：用户必须具有 "READ_PRIVILEGE" 权限。
//    "hasAnyAuthority('READ_PRIVILEGE', 'WRITE_PRIVILEGE')"：用户必须具有 "READ_PRIVILEGE" 或 "WRITE_PRIVILEGE" 中的任一权限。
//    "isAuthenticated()"：用户必须已通过身份验证。
//    "permitAll"：所有用户都允许访问。
//
//    在LoginUser中我们将角色和权限都加入了authorities,其中ROLE_开头的就是角色,其他的是权限


//   如果未指定method属性，Spring MVC将处理所有请求方法，包括GET、POST、PUT、DELETE等。
    @GetMapping("/hello")
//    @PreAuthorize("hasAuthority('check')")
//    //自定义的权限功能
//    @PreAuthorize("@ex.hasAuthority('system:dept:list')")
//    @PreAuthorize("permitAll")
    public String hello(){
        long t1 = System.currentTimeMillis();
//        Thread.sleep(2000);
        commonService.doTask1();
        long t2 = System.currentTimeMillis();
        logger.info("task1方法耗时 {} ms" , t2-t1);
        return "hello";
    }




//    @Autowired
//    private MinioUtil minioUtil;
//    @Value("${minio.endpoint}")
//    private String address;
//    @Value("${minio.bucketName}")
//    private String bucketName;

//    @PostMapping("/upload")
//    public String upload(MultipartFile file,String filePath) {
//        List<String> upload = minioUtil.upload(new MultipartFile[]{file},filePath);
//        return address + "/" + bucketName + "/" +filePath+ upload.get(0);
//    }

//    @PostMapping("/uploadList")
//    public List<String> uploadList(MultipartFile[] file) {
//        List<String> upload = minioUtil.upload(file);
//        upload = upload.stream().map(it -> address + "/" + bucketName + "/" + it).collect(Collectors.toList());
//        return upload;
//    }

//    @GetMapping("/getUrl")
//    public String get(String objectName) {
//        return minioUtil.getUploadObjectUrl(objectName);
//
//    }
//    @GetMapping("/down")
//    public ResponseEntity<byte[]> down(String objectName) {
//        return minioUtil.download(objectName);
//    }


}
