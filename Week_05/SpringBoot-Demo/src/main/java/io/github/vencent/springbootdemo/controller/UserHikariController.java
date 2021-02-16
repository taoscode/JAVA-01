package io.github.vencent.springbootdemo.controller;

import io.github.vencent.springbootdemo.entity.User;
import io.github.vencent.springbootdemo.service.HikariUserService;
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
@RequestMapping("hikari/")
public class UserHikariController {
    @Autowired
    private HikariUserService hikariUserService;
    @GetMapping("/getAllUser")
    @ResponseBody
    public List getAllUser(){
        return hikariUserService.findUsers();
    }

    @PostMapping("/saveUser")
    public Boolean saveUser(@RequestBody User user){
        return hikariUserService.insertUser(user);
    }
    @GetMapping("/saveBatch/{count}")
    public long saveUserBatch(@PathVariable int count){
        return hikariUserService.saveBatch(count);
    }
    @PostMapping("/updateUser")
    public Boolean updateUser(@RequestBody User user){
        return hikariUserService.updateUser(user);
    }
    @DeleteMapping("/deleteUser/{name}")
    public Boolean delUser(@PathVariable String name){
        return hikariUserService.deleteUser(name);
    }
}
