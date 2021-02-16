package io.github.vencent.springbootdemo.service;

import io.github.vencent.springbootdemo.entity.User;

import java.util.List;

/**
 * UserService
 *
 * @author vencent
 * @create 2021-02-16 15:25
 **/
public interface IUserService {
    List<User> findUsers();
    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(String name);
    long saveBatch(int count);
}
