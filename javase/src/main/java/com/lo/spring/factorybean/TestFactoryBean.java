package com.lo.spring.factorybean;

import com.lo.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;










public class TestFactoryBean {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext acac=new AnnotationConfigApplicationContext(AppConfig.class);

        MyFactoryBean bean = acac.getBean(MyFactoryBean.class);
        System.out.println("get bean by class:"+bean);

        Object myFactoryBean = acac.getBean("myFactoryBean");
        System.out.println("get bean by name:"+myFactoryBean);

        Object myFactoryBean2 = acac.getBean("&myFactoryBean");
        System.out.println("get bean by &{name}:"+myFactoryBean2);
    }
}
