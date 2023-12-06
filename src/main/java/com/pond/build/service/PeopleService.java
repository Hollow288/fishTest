package com.pond.build.service;

import com.pond.build.model.Teacher;
import com.pond.build.model.User;

import java.util.List;

public interface PeopleService {
    Integer insertUserInfo(User user);

    Integer insertTeacherInfo(Teacher teacher);

    User getUserById(Integer id);

    void deleteUserById(Integer id);

    Teacher selectTeacherAndUserById(Integer id);

    Teacher selectTeacherById(Integer id);

    Integer batchInsertUsers(List<User> userList);

    Integer batchUpdateUsers(List<User> userList);
}
