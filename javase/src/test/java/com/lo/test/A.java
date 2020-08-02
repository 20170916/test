package com.lo.test;

public abstract class A {
    abstract void test1();
    void test2(){

    }

    public static void main(String[] args) {
        A a=new B();

    }
}

class B extends A{

    @Override
    void test1() {

    }
}


