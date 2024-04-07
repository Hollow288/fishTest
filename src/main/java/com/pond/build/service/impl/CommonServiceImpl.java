package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.CommonMapper;
import com.pond.build.model.LoginUser;
import com.pond.build.model.Response.MenuResponse;
import com.pond.build.model.ResponseResult;
import com.pond.build.model.User;
import com.pond.build.service.CommonService;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CommonServiceImpl implements CommonService {

    private static final Logger logger = LogManager.getLogger(Example.class);


    @Autowired
    private CommonMapper commonMapper;

    @Override
    public ResponseResult getAllItems(String fieldName){

        Map<String, Object> map = new HashMap<>();
        String fullPathFieldName = "com.pond.build.model." + fieldName;

        try {
            // 使用 Class.forName 获取类的 Class 对象
            Class<?> clazz = Class.forName(fullPathFieldName);

            // 获取类的默认构造器并创建对象
            Object instance = clazz.getDeclaredConstructor().newInstance();

            return new ResponseResult(HttpStatusCode.OK.getCode(),"获取字段信息成功！",instance);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatusCode.NOT_FOUND.getCode(),"获取字段信息错误！");
        }
    }

    /**
     * @SneakyThrows 注解被用于修饰 methodThatThrowsException 方法。
     * 这使得在方法体内部抛出的受检异常（Exception）被转换为不受检异常，无需显式地在方法签名中声明或捕获。
     */
    @Override
    @SneakyThrows
    //这里如果不指定线程池,就会使用默认的线程池
    @Async("asyncPoolTaskExecutor")
    public void doTask1() {
        Thread.sleep(20000);
        logger.info("这里是异步执行,已经睡了20000毫秒");
    }

    @Override
    public ResponseResult getAllRouterAndChildren() {

        List<Map<String, Object>> allRouterAndChildren = commonMapper.getAllRouterAndChildren();
        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功",allRouterAndChildren);
    }

    @Override
    public ResponseResult getAllMenuChildren() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        //所有菜单,子父级
        List<MenuResponse> allMenuAndChildren = commonMapper.getAllMenuAndChildren();
        //该人员拥有的所有菜单menuId
        List<String> menuIds = commonMapper.getAllMenuIdByUserId(userInfo.getUserId().toString());
//        allMenuAndChildren.stream().filter(map->map.get("meanuId"))
        List<String> parentMenuIds = commonMapper.selectMenuParentId(menuIds);

        //最终用户拥有权限的菜单ID集合
        List<String> mergedList = Stream.concat(menuIds.stream(), parentMenuIds.stream())
                .distinct() // 去重
                .filter(id -> !id.equals("0")) // 过滤掉值为"0"的元素
                .collect(Collectors.toList());

//        allMenuAndChildren.stream().filter(map->map.get("menuId"))

        //所有用户拥有权限的菜单和子级的集合
        List<MenuResponse> filteredMenuAndChildren = allMenuAndChildren.stream()
                .filter(menuResponse -> mergedList.contains(menuResponse.getMenuId())) // 过滤出 menuId 在 mergedList 中存在的 MenuResponse
                .map(menuResponse -> { // 根据 mergedList 中是否存在 menuId 来过滤 children
                    List<MenuResponse> children = menuResponse.getChildren().stream()
                            .filter(child -> mergedList.contains(child.getMenuId()))
                            .collect(Collectors.toList());
                    menuResponse.setChildren(children);
                    return menuResponse;
                })
                .collect(Collectors.toList());

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功",filteredMenuAndChildren);
    }

    @Override
    public ResponseResult getAllPortalPortfolio(String type,Integer page, Integer pageSize) {


        List<Object> objects = new ArrayList<>();
        HashMap<String, Object> imgUrlMap = new HashMap<>();
        imgUrlMap.put("thumbnailUrl","https://img0.baidu.com/it/u=1730710238,1350681003&fm=253&fmt=auto&app=138&f=JPEG?w=480&h=296");
        imgUrlMap.put("completeUrl","https://img0.baidu.com/it/u=2397564216,1250084532&fm=253&fmt=auto&app=138&f=PNG?w=500&h=500");
        HashMap<String, Object> imgUrlMap1 = new HashMap<>();
        imgUrlMap1.put("thumbnailUrl","https://img0.baidu.com/it/u=1730710238,1350681003&fm=253&fmt=auto&app=138&f=JPEG?w=480&h=296");
        imgUrlMap1.put("completeUrl","https://img0.baidu.com/it/u=2397564216,1250084532&fm=253&fmt=auto&app=138&f=PNG?w=500&h=500");
        HashMap<String, Object> imgUrlMap2 = new HashMap<>();
        imgUrlMap2.put("thumbnailUrl","https://img0.baidu.com/it/u=1730710238,1350681003&fm=253&fmt=auto&app=138&f=JPEG?w=480&h=296");
        imgUrlMap2.put("completeUrl","https://img0.baidu.com/it/u=2397564216,1250084532&fm=253&fmt=auto&app=138&f=PNG?w=500&h=500");
        HashMap<String, Object> imgUrlMap3 = new HashMap<>();
        imgUrlMap3.put("thumbnailUrl","https://img0.baidu.com/it/u=1730710238,1350681003&fm=253&fmt=auto&app=138&f=JPEG?w=480&h=296");
        imgUrlMap3.put("completeUrl","https://img0.baidu.com/it/u=2397564216,1250084532&fm=253&fmt=auto&app=138&f=PNG?w=500&h=500");
        HashMap<String, Object> imgUrlMap4 = new HashMap<>();
        imgUrlMap4.put("thumbnailUrl","https://img0.baidu.com/it/u=1730710238,1350681003&fm=253&fmt=auto&app=138&f=JPEG?w=480&h=296");
        imgUrlMap4.put("completeUrl","https://img0.baidu.com/it/u=2397564216,1250084532&fm=253&fmt=auto&app=138&f=PNG?w=500&h=500");
        objects.add(imgUrlMap);
        HashMap<String, Object> imgUrlMap5 = new HashMap<>();
        imgUrlMap5.put("thumbnailUrl","https://img0.baidu.com/it/u=1730710238,1350681003&fm=253&fmt=auto&app=138&f=JPEG?w=480&h=296");
        imgUrlMap5.put("completeUrl","https://img0.baidu.com/it/u=2397564216,1250084532&fm=253&fmt=auto&app=138&f=PNG?w=500&h=500");
        objects.add(imgUrlMap);
        objects.add(imgUrlMap1);
        objects.add(imgUrlMap2);
        objects.add(imgUrlMap3);
        objects.add(imgUrlMap4);
        objects.add(imgUrlMap5);
        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功",objects);
    }

}
