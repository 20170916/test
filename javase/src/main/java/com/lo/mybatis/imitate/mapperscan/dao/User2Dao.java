package com.lo.mybatis.imitate.mapperscan.dao;

import com.lo.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface User2Dao {
    @Select("select * from t_user2")
    List<User> findAll();
}
