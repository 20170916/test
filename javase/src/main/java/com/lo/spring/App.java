package com.lo.spring;

import com.lo.pojo.User;
import com.lo.service.UserServiceImpl;
import com.lo.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;
import java.util.Map;

//@EnableAspectJAutoProxy
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
//        Entitlement ent = (Entitlement)ctx.getBean("entitlement");
//        System.out.println(ent);
//        System.out.println(ent.getName());
//        System.out.println(ent.getTime());
//
//        Entitlement ent2 = (Entitlement)ctx.getBean("entitlement2");
//        System.out.println(ent2);
//        System.out.println(ent2.getName());
//        System.out.println(ent2.getTime());
//
//        TestServiceImpl testService = ctx.getBean(TestServiceImpl.class);
//        System.out.println(testService);
//        ctx.close();
        /*UserServiceImpl bean = ctx.getBean(UserServiceImpl.class);
        final List<User> query = bean.query();
        System.out.println(query);
        final List<User> query2 = bean.query();
        System.out.println(query2);*/
        //会打印多条sql语句，因为Spring把mybatis的一级缓存关掉了
        System.out.println(ctx.getBean(UserServiceImpl.class).query());
        System.out.println(ctx.getBean(UserServiceImpl.class).query());
        System.out.println(ctx.getBean(UserServiceImpl.class).query());
        System.out.println(ctx.getBean(UserServiceImpl.class).query());
        //System.out.println(.query());

    }
}
