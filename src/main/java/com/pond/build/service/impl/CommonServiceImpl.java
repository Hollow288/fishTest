package com.pond.build.service.impl;

import com.pond.build.enums.HttpStatusCode;
import com.pond.build.model.ResponseResult;
import com.pond.build.service.CommonService;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {

    private static final Logger logger = LogManager.getLogger(Example.class);

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

}
