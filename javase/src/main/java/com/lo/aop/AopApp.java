package com.lo.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
        final A a = ctx.getBean("AImpl",A.class);
        a.a();
        System.out.println(a);
    }
}
