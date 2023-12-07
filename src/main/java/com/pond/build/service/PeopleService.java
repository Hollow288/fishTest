package com.pond.build.service;

import com.pond.build.model.Student;
import com.pond.build.model.Teacher;

import java.util.List;

public interface PeopleService {
    Integer insertStudentInfo(Student student);

    Integer insertTeacherInfo(Teacher teacher);

    Student getStudentById(Integer id);

    void deleteStudentById(Integer id);

    Teacher selectTeacherAndStudentById(Integer id);

    Teacher selectTeacherById(Integer id);

    Integer batchInsertStudents(List<Student> studentList);

    Integer batchUpdateStudents(List<Student> studentList);
}
