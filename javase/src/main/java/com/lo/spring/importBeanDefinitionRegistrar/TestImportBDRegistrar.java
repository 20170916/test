package com.lo.spring.importBeanDefinitionRegistrar;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestImportBDRegistrar {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(ImportBDRegistrarAppconfig.class);
        System.out.println(ctx.getBean("registerUserDao"));

    }
}
