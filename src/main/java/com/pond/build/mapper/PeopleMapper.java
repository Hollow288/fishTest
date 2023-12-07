package com.pond.build.mapper;
import com.pond.build.model.Teacher;
import com.pond.build.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PeopleMapper {
    Integer insertStudent(Student student);

    Integer insertTeacher(Teacher teacher);

    Integer updateTeacher(Teacher teacher);

    void deleteStudentById(@Param("id") Integer id);

    Teacher selectTeacherAndStudentById(@Param("id") Integer id);


    Student getStudentById(@Param("id") Integer id);

    Teacher getTeacher(@Param("id") Integer id);

    Integer batchInsertStudent(List<Student> studentList);

    Integer batchUpdatetStudent(List<Student> studentList);
}
