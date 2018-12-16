package com.lo.exception;

import org.junit.Test;

public class TestFinally {

    public static String testFinally() {
        try {
            System.out.println("try block");
            //return test1();
        } finally {
            System.out.println("finally block");
            // return "finally";
        }
        System.out.println("after finally");
        return "tail";
    }

    public static String test1() {
        System.out.println("return statement");
        return "after return";
    }

    @Test
    public void test(){
        testFinally();
    }
}
