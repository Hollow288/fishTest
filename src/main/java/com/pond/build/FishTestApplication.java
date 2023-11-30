package com.pond.build;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.pond.build.mapper")
@EnableTransactionManagement
public class FishTestApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FishTestApplication.class, args);
    }


    /**
     * java.lang.UnsupportedClassVersionError: org/springframework/web/SpringServletContainerInitializer :
     * Unsupported major.minor version 52.0 (无法载入的类 [org.springframework.web.SpringServletContainerInitializer])
     *
     * tomcat对应的jdk要1.8或者以上
     */

    /**
     * 如果项目是java11 那么部署tomcat时,系统的环境变量也要是java11
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(FishTestApplication.class);
    }

}