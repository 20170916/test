package com.lo.spring.importBeanDefinitionRegistrar;


import com.lo.dao.UserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestImportBDRegistrar {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(ImportBDRegistrarAppconfig.class);
        UserDao registerUserDao = (UserDao) ctx.getBean("registerUserDao");
        System.out.println(registerUserDao.findAll());

    }
}
