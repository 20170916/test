package com.lo.spring.factorybean;

public class MySpringBean {
    public MySpringBean() {
        System.out.println("create ------"+this.getClass().getSimpleName());
    }
}
