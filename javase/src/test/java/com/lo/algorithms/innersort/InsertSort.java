package com.lo.algorithms.innersort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 插入排序：
 * 将第一个元素看做有序，从第二个元素起，每一趟将一个待排序元素插入到有序序列中。
 * 使有序序列逐渐扩大，直到所有待排元素都插入到有序序列中，即完成插入排序。<br>
 * 常用的插入排序有：直接插入排序，希尔排序。<br>
 * 不常用插入排序：折半插入排序，二路插入排序，表插入排序。<br>
 */
public class InsertSort {
    static int [] arr={3,1,5,8,2,9,6,7,4,0};

    /**
     * 直接插入排序。
     * 数量小时效率高，数量多时效率低。
     * @param arr
     */
    private static void straightInsertSort(int [] arr){
        //i代表无序区即将插入到有序区的首元素所在的索引
        for(int i=1;i<arr.length;i++){
            int insertData=arr[i];
            //j代表有序区的索引，从尾部逐步向前找合适的插入点
            int j=i-1;
            //j>=0并且insertData<j执行的元素时，j所在元素后移
            while(j>=0&&insertData<arr[j]){
                arr[j+1]=arr[j--];
            }
            //每一趟结束时，insetData的插入点是j索引后面的位置
            arr[j+1]=insertData;
        }
    }
    @Test
    public void testStraightInsert(){
        System.out.println(Arrays.toString(arr));
        straightInsertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 折半插入排序：对直接插入的插入过程进行优化。
     * 插入时，利用折半查找确定插入位置。
     * @param arr
     */
    public static void binaryInsertSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int insertData=arr[i];
            int j=i-1;
            int low=0;
            int high=j;
            while(low<=high){
                int m =(low+high)/2;
                if(insertData<arr[m]){
                    high=m-1;
                }
                if(insertData>=arr[m]){
                    low=m+1;
                }
            }
            //结束的时候，insertDate一定是大于等于arr[high]，小于等于arr[low]的数，high+1与low相等；
            //所以插入点一定是low指定的索引位置
            while(j>=low){
                arr[j+1]=arr[j--];
            }
            arr[low]=insertData;
        }
    }

    @Test
    public void testBinaryInsertSort(){
        System.out.println(Arrays.toString(arr));
        binaryInsertSort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
