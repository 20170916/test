package com.lo.concurrent.hashmap;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    Map<String,String> map=new HashMap<>();

    @Test
    public void test(){


    }

    private void createThread(){
        Thread thread=new Thread(() -> {
            //map.put()
        });
        thread.start();
    }


}
