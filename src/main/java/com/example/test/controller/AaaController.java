package com.example.test.controller;


import com.example.test.model.Student;
import com.example.test.service.Play;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AaaController {

    @Autowired
    //service接口的多个实现，给service起名指定注入
//    @Qualifier("isMyNeedService")
    //或者注入bean列表
    private List<Play> play;

    @GetMapping("/user")
    public void abcde(@RequestParam Integer id){
        System.out.println(id);
        System.out.println("-------------------------");
        for (Play littlePlay : play) {
            System.out.println("-------------------------");
            littlePlay.doing();

        }
    }


    @PostMapping("/student")
    public void efghd(@RequestBody Student student){
        System.out.println(student);
        System.out.println("this is test tag");
    }


}
