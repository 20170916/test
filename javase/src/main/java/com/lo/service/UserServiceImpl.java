package com.lo.service;

import com.lo.dao.UserDao;
import com.lo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    public List<User> query(){
        return userDao.findAll();
    }
}
