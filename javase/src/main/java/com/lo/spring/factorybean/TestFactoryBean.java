package com.lo.spring.factorybean;

import com.lo.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;










public class TestFactoryBean {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext acac=new AnnotationConfigApplicationContext(AppConfig.class);

        MySpringBean bean = acac.getBean(MySpringBean.class);
        System.out.println("get bean by class:"+bean);

        final MyFactoryBean bean1 = acac.getBean(MyFactoryBean.class);
        System.out.println("get bean by FactoryBean class:"+bean1);

        Object myFactoryBean = acac.getBean("myFactoryBean");
        System.out.println("get bean by name:"+myFactoryBean);

        Object myFactoryBean2 = acac.getBean("&myFactoryBean");
        System.out.println("get bean by &{name}:"+myFactoryBean2);
    }
}
