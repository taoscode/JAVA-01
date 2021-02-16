package io.github.vencent.springbootdemo.controller;

import io.github.vencent.springbootdemo.entity.User;
import io.github.vencent.springbootdemo.service.JdbcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User Controller
 *
 * @author vencent
 * @create 2021-02-16 15:44
 **/
@RestController
@RequestMapping("jdbc/")
public class UserJdbcController {
    @Autowired
    private JdbcUserService jdbcUserService;
    @GetMapping("/getAllUser")
    @ResponseBody
    public List getAllUser(){
        return jdbcUserService.findUsers();
    }

    @PostMapping("/saveUser")
    public Boolean saveUser(@RequestBody User user){
        return jdbcUserService.insertUser(user);
    }
    @GetMapping("/saveBatch/{count}")
    public long saveUserBatch(@PathVariable int count){
        return jdbcUserService.saveBatch(count);
    }
    @PostMapping("/updateUser")
    public Boolean updateUser(@RequestBody User user){
        return jdbcUserService.updateUser(user);
    }
    @DeleteMapping("/deleteUser/{name}")
    public Boolean delUser(@PathVariable String name){
        return jdbcUserService.deleteUser(name);
    }
}
