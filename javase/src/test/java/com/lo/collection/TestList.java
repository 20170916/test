package com.lo.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestList {
    static List<Integer> list= new ArrayList<Integer>();
    @Test
    public void testDynamicAddRm(){
        list.add(1);
        list.add(2);list.add(3);
        for (int i=0;i<list.size();i++){
            System.out.println("i:"+i);
            System.out.println(list.get(i));
            if(list.get(i)==3){
                list.add(4);
            }
            System.out.println();
        }
    }
}
