package com.pond.build.service.impl;

import com.pond.build.mapper.PeopleMapper;
import com.pond.build.model.Teacher;
import com.pond.build.model.User;
import com.pond.build.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public Integer insertUserInfo(User user) {
        Integer insert = peopleMapper.insertUser(user);
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
    public void deleteUserById(Integer id) {
        peopleMapper.deleteUserById(id);
    }

    @Override
    @Cacheable(value="TeacherAndUserInfoById", key = "#id")
    public Teacher selectTeacherAndUserById(Integer id) {
        return  peopleMapper.selectTeacherAndUserById(id);
    }

    @Override
    public Teacher selectTeacherById(Integer id) {
        //开始事务后，可以看到mybatis的一级缓存生效了，并不会去查询两次数据库
        Teacher teacher = peopleMapper.getTeacher(id);
        //如果我在这里打上断点，在中途取修改了数据库的数据，那么mybatis依然读取的是一级缓存中的数据，这时候就要用到分布式的知识了
        System.out.println(teacher);


        //但是如果我在中途修改了这条数据,那么一级缓存将会被清空,下面输出的则是"Teacher{id=1, name='这是事务中加入的teacher', users=null}"
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
    public Integer batchInsertUsers(List<User> userList) {
     return   peopleMapper.batchInsertUser(userList);
    }

    @Override
    public Integer batchUpdateUsers(List<User> userList) {
      return  peopleMapper.batchUpdatetUser(userList);

    }
}
