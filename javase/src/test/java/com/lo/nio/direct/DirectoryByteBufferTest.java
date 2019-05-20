package com.lo.nio.direct;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

public class DirectoryByteBufferTest {

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    /**
     * 1.-Xmx100m,-XX:MaxDirectMemorySize默认也是100m。
     *  申请了128m的堆外内存，导致内存溢出：java.lang.OutOfMemoryError: Direct buffer memory
     * 2.-Xmx200m
     *  不会内存溢出
     * 3.-Xmx200m -XX:MaxDirectMemorySize=100m
     *  限制了堆外内存大小，导致溢出
     * @throws InterruptedException
     */
    public static void test1() throws InterruptedException {
        //分配128MB直接内存
        print();
        ByteBuffer bb = ByteBuffer.allocateDirect(1024*1024*128);

        TimeUnit.SECONDS.sleep(10);
        bb=null;
        for(int i=0;i<10;i++){
            print();
        }
        System.out.println("ok");
    }

    private static void print() throws InterruptedException {
        System.out.println(Runtime.getRuntime().totalMemory()+" "+Runtime.getRuntime().maxMemory()+" "+Runtime.getRuntime().freeMemory());
        TimeUnit.SECONDS.sleep(1);
    }

    public static void test2(){

    }
}
