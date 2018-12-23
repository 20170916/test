package com.lo.annotation;

import org.junit.Test;

@TestAnnotaion
public class TestAnnotation {
    @Test
    public void test(){
        boolean isAnnotation = TestAnnotation.class.isAnnotationPresent(TestAnnotaion.class);
        if(isAnnotation){
            TestAnnotaion annotation = TestAnnotation.class.getAnnotation(TestAnnotaion.class);
            System.out.println(annotation.id());
            System.out.println(annotation.msg());
        }
    }
}
