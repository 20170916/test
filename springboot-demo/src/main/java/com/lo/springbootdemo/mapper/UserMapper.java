package com.lo.springbootdemo.mapper;

import com.lo.springbootdemo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from t_user")
    List<User> findAll();

    @Insert("INSERT INTO t_user ( username, password) VALUES (#{username}, #{password})")
    void insert(User user);


}
