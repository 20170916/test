package com.lo.spring;

import com.lo.spring.config.AppConfig;
import com.lo.spring.pojo.Entitlement;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
        Entitlement ent = (Entitlement)ctx.getBean("entitlement");
        System.out.println(ent.getName());
        System.out.println(ent.getTime());

        Entitlement ent2 = (Entitlement)ctx.getBean("entitlement2");
        System.out.println(ent2.getName());
        System.out.println(ent2.getTime());

        ctx.close();

    }
}
