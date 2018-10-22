package com.lo.springbootdemo.service;

import com.lo.springbootdemo.entity.User;
import com.lo.springbootdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {

    List<User> findAll();
}
