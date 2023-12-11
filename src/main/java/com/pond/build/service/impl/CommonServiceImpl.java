package com.pond.build.service.impl;

import com.pond.build.model.ResponseResult;
import com.pond.build.service.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {


    @Override
    public ResponseResult getAllItems(String fieldName){

        Map<String, Object> map = new HashMap<>();
        String fullPathFieldName = "com.pond.build.model." + fieldName;

        try {
            // 使用 Class.forName 获取类的 Class 对象
            Class<?> clazz = Class.forName(fullPathFieldName);

            // 获取类的默认构造器并创建对象
            Object instance = clazz.getDeclaredConstructor().newInstance();

            return new ResponseResult(HttpStatus.OK.value(),"获取字段信息成功！",instance);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.NOT_FOUND.value(),"获取字段信息错误！");
        }
    }
}
