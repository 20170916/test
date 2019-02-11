package com.lo.spring.beanFactoryPostProcessor;

import com.lo.dao.UserDao;
import com.lo.pojo.User;
import com.lo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User2ServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    public List<User> query(){
        return userDao.findAll();
    }
}
