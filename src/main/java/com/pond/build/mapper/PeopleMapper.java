package com.pond.build.mapper;
import com.pond.build.model.Teacher;
import com.pond.build.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PeopleMapper {
    Integer insertUser(User user);

    Integer insertTeacher(Teacher teacher);

    Integer updateTeacher(Teacher teacher);

    void deleteUserById(@Param("id") Integer id);

    Teacher selectTeacherAndUserById(@Param("id") Integer id);


    User getUser(@Param("id") Integer id);

    Teacher getTeacher(@Param("id") Integer id);

    Integer batchInsertUser(List<User> userList);

    Integer batchUpdatetUser(List<User> userList);
}
