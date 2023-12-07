package com.pond.build.controller;

import com.pond.build.model.Student;
import com.pond.build.model.Teacher;
import com.pond.build.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PeopleController {
    @Autowired
    private PeopleService studentService;

    @Value("${pond.name}")
    private String pondName;


    //redis-server.exe redis.windows.conf

//    Redis默认拥有16个数据库，初始默认使用0号库，在命令行中通过select命令将数据库切换到8号数据库：
//
//    select 8

    //在命令中通过shutdown命令来关闭redis服务




    //添加单个student
    @PostMapping("/insertStudent")
    public void insertInfoByStudent(@RequestBody Student student){
        Integer integer = studentService.insertStudentInfo(student);
        System.out.println("==========================在这之前的操作===================");
        //注意：如果在这里抛出的异常，但是我们只在ServiceImpl中加了@Transactional注解，
        // controller层没有@Transactional注解，那么事务将不会回滚，因为事务已经提交了
//        int i = 1/0;
        //影响的行数
        System.out.println("influenceNum=" + integer);
        //传进来的student的id为null，但是这里就已经自动附上值了
        System.out.println("student=" + student);
    }

    @GetMapping("/getStudentById")
    public void getStudentById(@RequestParam Integer id){
        Student studentById = studentService.getStudentById(id);
        System.out.println(studentById);
    }


    @PostMapping("/insertTeacher")
    public void insertInfoByTeacher(@RequestBody Teacher teacher){
        Integer integer = studentService.insertTeacherInfo(teacher);
        System.out.println("==========================在这之前的操作===================");
        //注意：如果在这里抛出的异常，但是我们只在ServiceImpl中加了@Transactional注解，
        // controller层没有@Transactional注解，那么事务将不会回滚，因为事务已经提交了
//        int i = 1/0;
        //影响的行数
        System.out.println("influenceNum=" + integer);
        //传进来的student的id为null，但是这里就已经自动附上值了
        System.out.println("teacher=" + teacher);
    }

    //添加多个student
    @PostMapping("/batchInsertStudent")
    public void batchInsertStudent(@RequestBody List<Student> students){
        Integer integer = studentService.batchInsertStudents(students);
        //影响的行数
        System.out.println("influenceNum=" + integer);
        //传进来的students的id为null，但是这里就已经自动附上值了
        System.out.println("student=" + students);
    }


    //单个删除student
    @DeleteMapping("/deleteStudentById")
    public void deleteStudentById(@RequestParam Integer id){
        studentService.deleteStudentById(id);
    }

    //查单个teacher,顺带对应的student
    @GetMapping("/selectTeacherAndStudentById")
    public Teacher selectTeacherAndStudentById(@RequestParam Integer id){
        //这里为什么会创建两次Creating a new SqlSession呢 ，事务好像也是两个
//       Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@207fc7cc]
//       Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@30f9155d]

        //因为controller层没有@Transactional注解，所以这两次的查询是两个SqlSession，所以不仅事务不生效，mybatis的一级缓存也不生效

        Teacher teacher = studentService.selectTeacherAndStudentById(id);
        System.out.println(teacher);
//        Teacher teacher2 = studentService.selectTeacherAndStudentById(id);
//        System.out.println(teacher2);
        System.out.println("yml中的配置 pond.name = " + pondName);
        return teacher;
    }



    //查单个teacher信息，没有student
    @GetMapping("/selectTeacherById")
    public void selectTeacherById(@RequestParam Integer id){
        Teacher teacher = studentService.selectTeacherById(id);
//        System.out.println(teacher);
    }


    //修改多个student信息
    @PutMapping("/batchUpdateStudent")
    public void batchUpdateStudent(@RequestBody List<Student> students){
        Integer integer = studentService.batchUpdateStudents(students);
        //影响的行数 这里只会返回1 因为是一条条执行的
        System.out.println("influenceNum=" + integer);
        //传进来的students的id为null，但是这里就已经自动附上值了
        System.out.println("student=" + students);
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
