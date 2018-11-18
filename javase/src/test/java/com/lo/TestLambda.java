package com.lo;

import org.junit.Test;

import javax.swing.*;
import java.util.Arrays;
import java.util.Date;

public class TestLambda {

    @Test
    public void testSortUseLambda(){
        String[] arr=new String[]{"a12","b2312","c123","z12","f1","e"};
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr,(first,second) -> first.length()-second.length());
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test(){
        Timer t =new Timer(1000, event -> System.out.println("the time is"+new Date()));
        t.start();

    }
}
