package com.pond.build.controller;

import com.pond.build.model.Teacher;
import com.pond.build.model.User;
import com.pond.build.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PeopleController {
    @Autowired
    private PeopleService userService;

    @Value("${pond.name}")
    private String pondName;


    //添加单个user
    @PostMapping("/insertUser")
    public void insertInfoByUser(@RequestBody User user){
        Integer integer = userService.insertUserInfo(user);
        System.out.println("==========================在这之前的操作===================");
        //注意：如果在这里抛出的异常，但是我们只在ServiceImpl中加了@Transactional注解，
        // controller层没有@Transactional注解，那么事务将不会回滚，因为事务已经提交了
//        int i = 1/0;
        //影响的行数
        System.out.println("influenceNum=" + integer);
        //传进来的user的id为null，但是这里就已经自动附上值了
        System.out.println("user=" + user);
    }


    @PostMapping("/insertTeacher")
    public void insertInfoByTeacher(@RequestBody Teacher teacher){
        Integer integer = userService.insertTeacherInfo(teacher);
        System.out.println("==========================在这之前的操作===================");
        //注意：如果在这里抛出的异常，但是我们只在ServiceImpl中加了@Transactional注解，
        // controller层没有@Transactional注解，那么事务将不会回滚，因为事务已经提交了
//        int i = 1/0;
        //影响的行数
        System.out.println("influenceNum=" + integer);
        //传进来的user的id为null，但是这里就已经自动附上值了
        System.out.println("teacher=" + teacher);
    }

    //添加多个user
    @PostMapping("/batchInsertUser")
    public void batchInsertUser(@RequestBody List<User> users){
        Integer integer = userService.batchInsertUsers(users);
        //影响的行数
        System.out.println("influenceNum=" + integer);
        //传进来的users的id为null，但是这里就已经自动附上值了
        System.out.println("user=" + users);
    }


    //单个删除user
    @DeleteMapping("/deleteUserById")
    public void deleteUserById(@RequestParam Integer id){
        userService.deleteById(id);
    }

    //查单个teacher,顺带对应的user
    @GetMapping("/selectTeacherAndUserById")
    public Teacher selectTeacherAndUserById(@RequestParam Integer id){
        //这里为什么会创建两次Creating a new SqlSession呢 ，事务好像也是两个
//       Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@207fc7cc]
//       Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@30f9155d]

        //因为controller层没有@Transactional注解，所以这两次的查询是两个SqlSession，所以不仅事务不生效，mybatis的一级缓存也不生效

        Teacher teacher = userService.selectTeacherAndUserById(id);
        System.out.println(teacher);
//        Teacher teacher2 = userService.selectTeacherAndUserById(id);
//        System.out.println(teacher2);
        System.out.println("yml中的配置 pond.name = " + pondName);
        return teacher;
    }



    //查单个teacher信息，没有user
    @GetMapping("/selectTeacherById")
    public void selectTeacherById(@RequestParam Integer id){
        Teacher teacher = userService.selectTeacherById(id);
//        System.out.println(teacher);
    }


    //修改多个user信息
    @PutMapping("/batchUpdateUser")
    public void batchUpdateUser(@RequestBody List<User> users){
        Integer integer = userService.batchUpdateUsers(users);
        //影响的行数 这里只会返回1 因为是一条条执行的
        System.out.println("influenceNum=" + integer);
        //传进来的users的id为null，但是这里就已经自动附上值了
        System.out.println("user=" + users);
    }


    //限定get访问
    @RequestMapping(value = "/second", method = RequestMethod.GET)
    public Map<String, Object> requestMappingTest(){
        Map<String, Object> map = new HashMap<>();
        map.put("flag", "F");
        map.put("rtn", map);
//        System.out.println(map);
        System.out.println("1111=================================");
        return map;
    }


    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public Map<String, Object> requestMappingTest2(){
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("flag", "F");
            int i = 1/0;
//        System.out.println(map);
        } catch (Exception e) {
            //如果是这种,将不会输出下面的1111
            throw new RuntimeException(e);
            //如果是这种,将会继续执行下面的代码
//            e.printStackTrace();
        }
        System.out.println("1111=================================");
        return map;
    }


}
