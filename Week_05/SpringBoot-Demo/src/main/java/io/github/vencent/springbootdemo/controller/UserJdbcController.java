package io.github.vencent.springbootdemo.controller;

import io.github.vencent.springbootdemo.entity.User;
import io.github.vencent.springbootdemo.service.JdbcPreStatementUserService;
import io.github.vencent.springbootdemo.service.JdbcStatementUserService;
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
    private JdbcPreStatementUserService jdbcPreStatementUserService;
//    private JdbcStatementUserService jdbcPreStatementUserService;
    @GetMapping("/getAllUser")
    @ResponseBody
    public List getAllUser(){
        return jdbcPreStatementUserService.findUsers();
    }

    @PostMapping("/saveUser")
    public Boolean saveUser(@RequestBody User user){
        return jdbcPreStatementUserService.insertUser(user);
    }
    @GetMapping("/saveBatch/{count}")
    public long saveUserBatch(@PathVariable int count){
        return jdbcPreStatementUserService.saveBatch(count);
    }
    @PostMapping("/updateUser")
    public Boolean updateUser(@RequestBody User user){
        return jdbcPreStatementUserService.updateUser(user);
    }
    @DeleteMapping("/deleteUser/{name}")
    public Boolean delUser(@PathVariable String name){
        return jdbcPreStatementUserService.deleteUser(name);
    }
}
