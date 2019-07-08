package com.lo.concurrent.thread;

import org.junit.Test;

public class ThreadTest {


    @Test
    public void test(){
        Thread thread=new Thread(()->{
            System.out.println(1);
        });
        thread.start();
        //一个线程不能启动两次，start方法运行前会检查线程状态是否为0，即是否为new状态
        //如果不是new状态，会抛出IllegalThreadStateException
        thread.start();
    }
}
