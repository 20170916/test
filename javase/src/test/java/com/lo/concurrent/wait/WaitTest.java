package com.lo.concurrent.wait;

import com.lo.Test;

public class WaitTest {

    public static class Service1{

    }
    public static class Service2{}
    Service1 service1=new Service1();
    Service2 service2=new Service2();

    /**
     * 主线程通过wait，释放对象锁。主线程进入阻塞状态，等待被唤醒后，继续执行。
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        WaitTest test=new WaitTest();
        System.out.println("main join");
        test.stop();


        test.join();

        System.out.println("end");

    }

    public WaitTest() throws InterruptedException {

    }

    public void stop() throws InterruptedException {
        Thread thread=new Thread(() -> {


            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (service1){

                service1.notify();
            }
            System.out.println("notify service1");
            synchronized (service2){

                service2.notifyAll();
            }
            System.out.println("notify service2");


        });
        thread.start();
    }



    /**
     * 调用wait方法需要获取其对象锁。
     * @throws InterruptedException
     */
    public void join() throws InterruptedException {
        synchronized (service1){
            service1.wait();
        }
        synchronized (service2){
            service2.wait();
        }
    }
}
