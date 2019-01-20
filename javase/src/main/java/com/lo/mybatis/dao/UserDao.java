package com.lo.mybatis.dao;

import com.lo.mybatis.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserDao {
    @Select("select * from t_user")
    List<Map<String,Object>> findAll();
}
