package io.github.vencent.springbootdemo.controller;

import io.github.vencent.entity.ISchool;
import io.github.vencent.entity.Klass;
import io.github.vencent.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 starter
 *
 * @author vencent
 * @create 2021-02-16 12:09
 **/
@RestController
public class TestStarter {
    @Autowired
    private ISchool school;
    @Autowired
    private Klass klass;
    @Autowired
    private Student student;
    @GetMapping("/hello")
    public String hello(){
        school.ding();
        klass.dong();
        student.print();
        return String.join(",",student.getApplicationContext().getBeanDefinitionNames());
    }
}
