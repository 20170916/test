package com.lo.concurrent.volatile_;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ResortTest {
    private int id=0;
    private List<Long> st=new ArrayList<>(100);
    private List<Long> et=new ArrayList<>(100);

    @Test
    public void test() throws InterruptedException {
        for(int i=0;i<100;i++){

            this.addId();
        }
        int sum=0;
        for (int i = 0; i < 100; i++) {
            sum+=(et.get(i)-st.get(i));
        }
        System.out.println(sum);
    }

    private void addId() throws InterruptedException {
        final long s = System.nanoTime();
        st.add(s);
        id++;

        final long e = System.nanoTime();
        et.add(e);
        //System.out.println(e-s);

    }
}
