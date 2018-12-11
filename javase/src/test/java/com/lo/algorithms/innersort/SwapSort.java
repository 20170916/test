package com.lo.algorithms.innersort;

import org.junit.Test;

import java.util.Arrays;

public class SwapSort {
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


    public void bubbleSort(int[] arr){
        for(int i=arr.length-1;i>0;i--){
            int lastSwapIndex=0;
            for(int j=0;j<i;j++){

                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                    lastSwapIndex=j+1;
                }
            }
            i=lastSwapIndex;
        }
    }

    @Test
    public void testBubbleSort(){
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 升序排序。<p>
     * 优化1：在对无序区进行一趟交换操作后，最后一次发生交换的位置以前（从后先前扫描无序区）的数据都是有序的。<br>
     * 优化2：对无序区的一趟扫描后，若未发生交换，说明无序区已经有序，这个序列已经有序。
     * @param arr
     */
    public void optimizeBubbleSort(int[] arr){
        //i是无序区头部索引（从后往前）；也是一趟交换后无序区的最值所在索引
        for(int i=0;i<arr.length-1;i++){
            //j是每趟无序区遍历的指针，k用来做优化逻辑，记录最后一次交换位置的索引
            int k=arr.length-1;
            for(int j=arr.length-1;j>i;j--){
                if(arr[j]<arr[j-1]){
                    swap(arr,j,j-1);
                    k=j-1;
                }
            }
            i=k-1;
        }
    }

    @Test
    public void testOptimizeBubbleSort(){
        System.out.println(Arrays.toString(arr));
        optimizeBubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
