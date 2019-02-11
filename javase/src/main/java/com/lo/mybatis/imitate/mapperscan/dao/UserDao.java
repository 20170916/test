package com.lo.mybatis.imitate.mapperscan.dao;

import com.lo.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    @Select("select * from t_user")
    List<User> findAll();
}
