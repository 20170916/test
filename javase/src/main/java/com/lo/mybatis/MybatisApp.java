package com.lo.mybatis;

import com.lo.mybatis.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class MybatisApp {
    private static Logger logger=LoggerFactory.getLogger(MybatisApp.class);
    public static void main(String[] args) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.getConfiguration().addMapper(UserDao.class);
        //多次查询，多个结果，但只有一个sql语句（一级缓存）
        System.out.println(sqlSession.getMapper(UserDao.class).findAll());
        sqlSession.clearCache();//关闭一级缓存
        System.out.println(sqlSession.getMapper(UserDao.class).findAll());
        System.out.println(sqlSession.getMapper(UserDao.class).findAll());
        System.out.println(sqlSession.getMapper(UserDao.class).findAll());
        //logger.info("1");
    }
}
