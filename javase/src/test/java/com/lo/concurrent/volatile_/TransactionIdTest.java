package com.lo.concurrent.volatile_;

import org.junit.Test;

public class TransactionIdTest {
    private volatile long id;

    @Test
    public void test() throws InterruptedException {
        this.createThread(3);


        synchronized (this){
            wait(1000);
        }
        System.out.println(id);
    }

    private void createThread(int num){
        for (int i = 0; i < num; i++) {
            Thread t=new Thread(()->{
                //this.addId();
                for (int j = 0; j < 4000; j++) {
                    this.addIdSyn();
                }

                System.out.println(Thread.currentThread().getName()+"end");

            },"t"+i);
            t.start();
        }
        Thread t=new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"start");
            while (!this.updateId()){
            }

            System.out.println(id+"--------");
            System.out.println(Thread.currentThread().getName()+"end");
        },"update");
        t.start();
    }

    private void addId(){
        id++;
    }
    private void addIdSyn(){
        synchronized (this){
            id++;
            //System.out.println(id);
        }

    }

    private synchronized boolean updateId(){
        if (id == 5000) {

            id = 6000;
            return true;
        }
        return false;
    }
}
