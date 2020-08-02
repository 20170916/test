package com.lo.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestConcurrentModificationException {

    @Test
    public void test(){
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);

        }
        System.out.println(list.size());

        /**
         * 增强for解语法糖后实际上是用迭代器实现的。
         *  创建迭代器时，会用modCount初始化expectModCount；
         *  对数组的增删操作会对modCount++；
         *  迭代器在next和remove方法执行前会检查modCount与expectModCount是否相等，不等就会抛出这个异常
         */
        try {
            for (Integer integer : list) {
                if (integer == 5) {
                    list.remove(5);
                }
            }
            System.out.println(list.size());
        }catch (Exception e){
            // remove会成功，异常是在迭代器的next方法检查时抛出的
            e.printStackTrace();
            System.out.println(list.size());
        }

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i)==2||list.get(i)==3){
                list.remove(i);//这里如果没有个i--,被删除数据的下一个位置会被跳过去
            }
        }
        System.out.println(list.size());
    }
}
