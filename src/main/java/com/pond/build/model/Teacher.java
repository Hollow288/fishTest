package com.pond.build.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * (Teacher)实体类
 *
 * @author makejava
 * @since 2023-11-23 11:30:11
 */
@Data
public class Teacher implements Serializable {
    private static final long serialVersionUID = -78591128067875728L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 学生
     */
    private List<Student> students;


    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}

