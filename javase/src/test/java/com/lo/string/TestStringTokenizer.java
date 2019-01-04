package com.lo.string;

import org.junit.Test;

import java.util.StringTokenizer;

public class TestStringTokenizer {

    @Test
    public void test(){
        String str="0-15 5 31 * ?";
        //分隔符参数delim可以指定多个，例如" /t"，就代表空格或制表符来分割
        StringTokenizer stringTokenizer = new StringTokenizer(str," -",true);
        while (stringTokenizer.hasMoreTokens()){
            String s = stringTokenizer.nextToken().trim();
            System.out.println(s);
        }
    }
}
