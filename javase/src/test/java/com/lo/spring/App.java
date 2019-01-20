package com.lo.spring;

import com.lo.spring.config.AppConfig;
import com.lo.spring.pojo.Entitlement;
import com.lo.spring.service.TestServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@EnableAspectJAutoProxy
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
        Entitlement ent = (Entitlement)ctx.getBean("entitlement");
        System.out.println(ent);
        System.out.println(ent.getName());
        System.out.println(ent.getTime());

        Entitlement ent2 = (Entitlement)ctx.getBean("entitlement2");
        System.out.println(ent2);
        System.out.println(ent2.getName());
        System.out.println(ent2.getTime());

        TestServiceImpl testService = ctx.getBean(TestServiceImpl.class);
        System.out.println(testService);
        ctx.close();

    }
}
