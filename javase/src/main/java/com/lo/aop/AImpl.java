package com.lo.aop;


import org.springframework.stereotype.Component;

@Component
public class AImpl implements A {
    @Override
    public void a() {
        System.out.println("a");
    }
}
