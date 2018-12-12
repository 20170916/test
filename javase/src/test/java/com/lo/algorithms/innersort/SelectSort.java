package com.lo.algorithms.innersort;

import org.junit.Test;

import java.util.Arrays;

public class SelectSort {

    static int [] arr={3,1,5,8,2,9,6,7,4,0};

    /**
     * 利用亦或操作交换数组中的两个个数据。<p>
     *     原理：a^(a^b)等于b；
     */
    public void swap(int[] arr,int i,int j){
        arr[i]^=arr[j];
        arr[j]^=arr[i];
        arr[i]^=arr[j];
    }

    /**
     * 直接选择排序<p>
     *
     * @param a int数组
     */
    public void selectSort(int[]a){
        //移动指针i用来表示有序区的终点索引（起点索引是0），也可以当做无序区的起点索引
        //当无序区只有两个元素时是最后一趟选择操作，也就是说i要小于a.length-1
        for(int i=0;i<a.length-1;i++){
            //每一趟无序区最小值的索引，初始化时指定为无序区第一个元素为最小值。
            int indexOfMin=i;
            for(int j=i+1;j<a.length;j++){
                if(a[j]<a[indexOfMin]){
                    indexOfMin=j;
                }
            }
            //每一趟过后，indexOfMin变量就会指向无序区的最小值
            //如果indexOfMin没被改变，还是指向无序区的首位置，那就不用交换了
            if(indexOfMin!=i){
                swap(a,indexOfMin,i);
            }
        }
    }

    @Test
    public void testSelectSort(){
        System.out.println(Arrays.toString(arr));
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
