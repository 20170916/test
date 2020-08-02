package com.lo.concurrent.atomic;

import java.lang.reflect.Field;
import java.util.*;

public class BaseTypeTest {
    static Integer count=0;
    static int sum=10000;
    static List<Integer> countThread=new Vector<>();

    public static void main(String[] args) throws InterruptedException {
        createThread(3);
        Thread.sleep(1000);
        System.out.println(count);
        assert countThread.size()==3;
        int tmp=0;
        for (Integer integer : countThread) {
            tmp+=integer;
        }
        System.out.println(tmp);
    }

    private static void createThread(int num){
        for (int j = 0; j < num; j++) {
            int finalJ = j;
            Thread t=new Thread(()->{
                List<Integer> list=new ArrayList<>(sum);
                while(count<sum){
                    int tmp;
                    synchronized (BaseTypeTest.class){

                        list.add(count);
                        tmp=count+1;
                    }
                    count=tmp;
                }
                countThread.add(list.size());
            });
            t.start();
        }
    }

    private static void add(int i) throws NoSuchFieldException {
        count++;
        final Field declaredField = BaseTypeTest.class.getDeclaredField("list" + i);



    }
}
