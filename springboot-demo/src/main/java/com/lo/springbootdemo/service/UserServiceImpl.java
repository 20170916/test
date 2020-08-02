package com.lo.springbootdemo.service;

import com.lo.springbootdemo.entity.User;
import com.lo.springbootdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service


public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    public List<User> findAll(){
        return userMapper.findAll();
    }

    @Transactional
    @Override
    public void insert(){
        final User user = User.builder()
                .password("123")
                .username("123")
                .build();
        userMapper.insert(user);
        int a = 1/0;
    }
}
