package io.github.vencent.multidatasource.controller;

import io.github.vencent.multidatasource.entity.User;
import io.github.vencent.multidatasource.service.IUserService;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/find")
    public List<User> findAll(){
        return userService.findAllUser();
    }
    @GetMapping("/add/{id}/{name}")
    public void insertUser(@PathVariable Long id,@PathVariable String name){
        userService.addUser(new User(id,name));
    }
}
