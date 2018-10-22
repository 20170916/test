package com.lo.springbootdemo.service;

import com.lo.springbootdemo.entity.User;
import com.lo.springbootdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    public List<User> findAll(){
        return userMapper.findAll();
    }
}
