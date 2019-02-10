package com.lo.mybatis;

import com.lo.config.AppConfig;
import com.lo.service.UserServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMybatisApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext acac=new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println(acac.getBean(UserServiceImpl.class).query());
        System.out.println(acac.getBean(UserServiceImpl.class).query());
        System.out.println(acac.getBean(UserServiceImpl.class).query());

    }
}
