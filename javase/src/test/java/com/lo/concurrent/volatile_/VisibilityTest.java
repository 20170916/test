package com.lo.concurrent.volatile_;

import org.junit.Test;

public class VisibilityTest {
    volatile boolean shutdownVisibility;

    @Test
    public void testVisibility(){

        startThread(10);
        shutdownVisibility=true;

        while (true){

        }
    }

    private void startThread(int num){
        for(int i =0;i<num;i++){
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!shutdownVisibility){
                        //do sth
                    }
                    System.out.println(Thread.currentThread().getName()+" over");
                }
            },"t-visibility"+i);
            t.start();
        }
    }

    boolean shutdownUnvisibility;

    @Test
    public void testUnvisibility(){

        startUnvisibilityThread(10);
        shutdownUnvisibility=true;

        while (true){

        }
    }

    private void startUnvisibilityThread(int num){
        for(int i =0;i<num;i++){
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!shutdownUnvisibility){
                        //do sth
                    }
                    System.out.println(Thread.currentThread().getName()+" over");
                }
            },"t-unvisibility"+i);
            t.start();
        }
    }

}
