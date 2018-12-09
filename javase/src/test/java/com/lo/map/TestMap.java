package com.lo.map;

import org.junit.Test;

import java.util.*;

public class TestMap {

    @Test
    public void test(){
        Map<String,Integer> map =new TreeMap();
        map.put("han",1);
        map.put("wu",2);
        map.put("ma",3);
        map.put("liu",4);
        map.put("liu",null);
        for(Map.Entry entry:map.entrySet()){
            System.out.println(entry.getKey()+"----"+entry.getValue());
        }
    }
}
