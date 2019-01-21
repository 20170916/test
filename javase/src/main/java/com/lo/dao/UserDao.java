package com.lo.dao;

import com.lo.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserDao {
    @Select("select * from t_user")
    List<User> findAll();
}
