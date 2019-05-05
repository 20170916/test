package com.lo.design.pattern;

import org.apache.commons.math3.analysis.function.Sin;

public class SingleTonTest {
    public static class SingleTon{
        private volatile static SingleTon singleTon;
        private SingleTon(){

        }
        public static SingleTon getInstance(){
            if(singleTon==null){
                synchronized (SingleTon.class){
                    if(singleTon==null){
                        singleTon=new SingleTon();
                    }
                }
            }
            return singleTon;
        }
    }
}
