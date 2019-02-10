package com.lo.mybatis;

import com.lo.dao.UserDao;
import com.lo.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class MybatisApp {
    private static Logger logger=LoggerFactory.getLogger(MybatisApp.class);
    public static void main(String[] args) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.getConfiguration().addMapper(UserDao.class);
        //多次查询，多个结果，但只有一个sql语句（mybatis默认打开了一级缓存）
        List<User> list = sqlSession.getMapper(UserDao.class).findAll();
        final User user = list.get(0);
        System.out.println(user);
        System.out.println(list);
        sqlSession.clearCache();//关闭一级缓存
        System.out.println(list);
        System.out.println(list);
        System.out.println(list);
        //logger.info("1");
    }
}
