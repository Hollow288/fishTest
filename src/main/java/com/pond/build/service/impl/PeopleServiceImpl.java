package com.pond.build.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pond.build.mapper.PeopleMapper;
import com.pond.build.model.Student;
import com.pond.build.model.Teacher;
import com.pond.build.service.PeopleService;
import com.pond.build.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleMapper peopleMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Integer insertStudentInfo(Student student) {
        Integer insert = peopleMapper.insertStudent(student);
        //有@Transactional 在这里抛出异常，事务会回滚
//        Object obj = null;
//        obj.toString();
//        int i = 1/0;
        return insert;
    }

    @Override
    public Integer insertTeacherInfo(Teacher teacher) {
        Integer insert = peopleMapper.insertTeacher(teacher);
        return insert;
    }


    @Override
    public Student getStudentById(Integer id){
        String key = "getStudentById::"+id;
        Object value = redisUtil.get(key);
        if (value != null){
            return JSONObject.parseObject(value.toString(), Student.class);
        }
        Student student = peopleMapper.getStudentById(id);
        redisUtil.set("getStudentById::"+id, JSONObject.toJSONString(student),3600);
        return student;
    }

    @Override
    //删除缓存
    @CacheEvict(value = "getStudentById", key = "#id")
    public void deleteStudentById(Integer id) {
        peopleMapper.deleteStudentById(id);
    }

    /**
     * @Cacheable(value = "myCache", key = "#id") 中的 value 属性表示缓存的名称。在Redis中，这个名称就是一个键空间（key space）
     * key = "#id" 中的 #id 是一个SpEL表达式，表示方法参数中名为 "id" 的值。这个值将被用作缓存键（在Redis中就是键），它是用于在缓存中唯一标识缓存项的。
     * redis中是这样的  TeacherAndStudentInfoById::1
     */
    @Override
    @Cacheable(value="selectTeacherAndStudentById", key = "#id")
    public Teacher selectTeacherAndStudentById(Integer id) {
        return  peopleMapper.selectTeacherAndStudentById(id);
    }

    @Override
    public Teacher selectTeacherById(Integer id) {
        //开始事务后，可以看到mybatis的一级缓存生效了，并不会去查询两次数据库
        Teacher teacher = peopleMapper.getTeacher(id);
        //如果我在这里打上断点，在中途取修改了数据库的数据，那么mybatis依然读取的是一级缓存中的数据，这时候就要用到分布式的知识了
        System.out.println(teacher);


        //但是如果我在中途修改了这条数据,那么一级缓存将会被清空,下面输出的则是"Teacher{id=1, name='这是事务中加入的teacher', students=null}"
        //清空一级缓存的条件是因为我只查了这一行，那么这一行的数据被修改，才会清空一级缓存
        //如果传进来的id为1，我去查id为1的数据，然后在中途去修改了id为3的数据，那么id为1的数据的一级缓存不会被清空
//        Teacher teacherInfo = new Teacher();
//        teacherInfo.setName("这是事务中加入的teacher");
//        teacherInfo.setId(1);
//        peopleMapper.updateTeacher(teacherInfo);
//

        Teacher teacher1 = peopleMapper.getTeacher(id);
        System.out.println(teacher1);
        return  teacher1;
    }

    @Override
    public Integer batchInsertStudents(List<Student> studentList) {
     return   peopleMapper.batchInsertStudent(studentList);
    }

    @Override
    public Integer batchUpdateStudents(List<Student> studentList) {
      return  peopleMapper.batchUpdatetStudent(studentList);
    }
}
