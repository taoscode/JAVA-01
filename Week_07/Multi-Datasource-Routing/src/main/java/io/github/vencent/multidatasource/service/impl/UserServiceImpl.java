package io.github.vencent.multidatasource.service.impl;

import io.github.vencent.multidatasource.entity.User;
import io.github.vencent.multidatasource.mapper.UserMapper;
import io.github.vencent.multidatasource.service.IUserService;
import io.github.vencent.multidatasource.tag.ReadOnly;
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
    private UserMapper userMapper;

    @Override
    @ReadOnly
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }
}
