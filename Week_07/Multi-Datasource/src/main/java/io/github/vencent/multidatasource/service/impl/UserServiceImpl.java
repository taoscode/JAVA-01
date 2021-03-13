package io.github.vencent.multidatasource.service.impl;

import io.github.vencent.multidatasource.entity.User;
import io.github.vencent.multidatasource.mapper.UserReadMapper;
import io.github.vencent.multidatasource.mapper.UserWriteMapper;
import io.github.vencent.multidatasource.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserWriteServiceImpl
 *
 * @author vencent
 * @create 2021-03-07 22:31
 **/
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserWriteMapper userWriteMapper;
    @Autowired
    private UserReadMapper userReadMapper;

    @Override
    public List<User> findAllUser() {
        return userReadMapper.findAllUser();
    }

    @Override
    public void addUser(User user) {
        userWriteMapper.addUser(user);
    }
}
