package com.lo.mybatis.imitate.mapperscan;


import com.lo.mybatis.imitate.mapperscan.dao.User2Dao;
import com.lo.mybatis.imitate.mapperscan.dao.UserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestImportBDRegistrar {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(Appconfig.class);
        UserDao registerUserDao = (UserDao) ctx.getBean("UserDao");
        System.out.println(registerUserDao.findAll());

        User2Dao user2Dao= (User2Dao) ctx.getBean("User2Dao");
        System.out.println(user2Dao.findAll());


    }
}
