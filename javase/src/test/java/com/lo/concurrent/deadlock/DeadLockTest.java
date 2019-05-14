package com.lo.concurrent.deadlock;

import org.junit.Test;

public class DeadLockTest {

    /**
     * 如果a和b是相同字符串，则对应相同的锁。不会发生死锁。
     */
    String a="a";
    String b="";


    public static void main(String[] args) {
        DeadLockTest deadLockTest=new DeadLockTest();
        Thread t1=new Thread(()->{
            try {
                deadLockTest.aThenB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        Thread t2=new Thread(()->{
            try {
                deadLockTest.bThenA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
    }

    private void aThenB() throws InterruptedException {
        synchronized (a){
            System.out.println("a then b:a");
            Thread.sleep(1000);
            synchronized (this){

                this.wait(1000);
            }
            synchronized (b){
                System.out.println("a then b:b");
            }
        }
    }

    private void bThenA() throws InterruptedException {
        synchronized (b){
            System.out.println("b then a:b");
            Thread.sleep(1000);
            synchronized (a){
                System.out.println("b then a:a");

            }
        }
    }
}
