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


    public void quickSort(int[] a){
        quickSort(a,0,a.length-1);
    }
    /**
     *快排。<p>
     *这是一个递归方法，递归的条件是s<e。<br>
     * @param a 待排序的数组。
     * @param s 待排序序列的首部索引。
     * @param e 待排序序列的尾部索引。
     */
    private void quickSort(int[]a,int s,int e){
        //当带排序序列个数大于等于2个的时候才会排序;
        if(s<e){
            //i、j两个指针分别从待排序序列首位同步相向遍历，
            //遍历过程中，两个指针一直处于相异的状态，
            // 即：一个指针处于枢轴位置，另一个指针的值与枢轴的值进行比较来决定向前移动指针还是交换指针的状态。
            int i=s;
            int j=e;
            //这里每一趟取带排序的首个元素为枢轴，枢轴只是一个值，枢轴也可以不是第一个元素（记得jdk中是取首中尾三个元素中间的那个作为枢轴）。
            int pivot=a[s];
            //只要i<j就要继续往下判断；
            while(i<j){
                while(i<j&&a[j]>=pivot){
                    j--;
                }
                if(i<j){
                    a[i++]=a[j];
                }

                while(i<j&&a[i]<=pivot){
                    i++;
                }
                if(i<j){
                    a[j--]=a[i];
                }
            }
            //i==j时结束
            a[i]=pivot;
            quickSort(a,s,i-1);
            quickSort(a,i+1,e);
        }
    }

    @Test
    public void testQuickSort(){
        System.out.println(Arrays.toString(arr));
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
