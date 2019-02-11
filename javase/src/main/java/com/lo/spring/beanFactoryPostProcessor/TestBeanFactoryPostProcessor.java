package com.lo.spring.beanFactoryPostProcessor;

import com.lo.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;








public class TestBeanFactoryPostProcessor {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext acac=new AnnotationConfigApplicationContext(AppConfig.class);


        Object userServiceImpl = acac.getBean("user2ServiceImpl");
        System.out.println("-- get bean by name:"+userServiceImpl);

        Object bean = acac.getBean(UserServiceImplAnother.class);
        System.out.println("-- get bean by class:"+bean);
        /*Object userServiceImpl2 = acac.getBean("&userServiceImpl");
        System.out.println("get bean by &{name}:"+userServiceImpl2);*/
    }
}
