package io.github.vencent.multidatasource.service;

import io.github.vencent.multidatasource.entity.User;

import java.util.List;
import java.util.Map;

/**
 * UserWriteService
 *
 * @author vencent
 * @create 2021-03-07 22:28
 **/
public interface IUserService {
    List<User> findAllUser();
    void addUser(User user);
}
