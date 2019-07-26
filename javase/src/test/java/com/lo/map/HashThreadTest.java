package com.lo.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *多线程条件，Hashmap和CurrentHashMap都会有数据丢失现象，只不过所谓线程安全的concurrentHashMap丢的更少一些
 */
public class HashThreadTest extends Thread{
    static Map<Integer, Integer> map=new HashMap<Integer, Integer>(1);
    //static  Map<Integer, Integer> map=new ConcurrentHashMap<Integer, Integer>();
    //static Map<Integer, Integer> map=
    static AtomicInteger aInteger=new AtomicInteger();
    //static List<Integer> list=new Vector<>();
    static List<Integer> list=new ArrayList<>();
    public void run(){
        while(aInteger.get()<10000){
            synchronized (HashThreadTest.class){

                //map.put(aInteger.get(),1);
                list.add(aInteger.get());
                //sysout操作非常耗时，加入将很难遇到冲突。
                //System.out.println(Thread.currentThread().getName()+"put了: "+aInteger.get());
                aInteger.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        HashThreadTest t=new HashThreadTest();
        int num=5;
        Thread[] threads=new Thread[num];
        for (int i = 0; i < num ; i++) {
            threads[i]=new Thread(t,"线程"+i);
            threads[i].start();
        }
        //默认还有个守护线程
        /*while (Thread.activeCount() > 2) {
            //  Thread.currentThread().getThreadGroup().list();
            Thread.yield();
        }*/
        Thread.sleep(2000);
        System.out.println(t.map.size());
        System.out.println(list.size());
        /*int count=0;
        for(int i=0;i<10000;i++){
            if(!t.map.containsKey(i)){
                count++;
                //System.out.println("key: "+i+" is not contains");
            }
        }
        System.out.println(t.map.size());
        System.out.println(count);*/

        System.out.println(aInteger.get());

    }
}
