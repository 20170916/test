package com.lo.constructor;

import org.junit.Test;

/**
 * 多态情形下，若子类对父类方法做了实现，无论父类还是子类调用该方法，即使是父类.this.方法()的形式，都是从子类开始向上找调用的方法。
 */
public class MethodPriority {
    public static class Parent{
        public Parent(){
            say();
            Parent.this.say();
        }

        public void say(){
            System.out.println("parent");
        }
    }

    public static class Children extends Parent{
        public void say(){
            System.out.println("children");
        }
    }

    @Test
    public void test(){
        Parent parent=new Children();
        parent.say();
    }
}
