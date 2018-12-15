package com.lo.algorithms.innersort;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BucketSort {

    static int [] arr={3,1,5,8,2,9,6,7,4,0};
    public static void radixSort(int [] a){
        List<Integer>[] buckets=new List[10];
        //用于记录处理第几位（个位为0，十位为1，百位为2...）
        int site=0;
        while(true){
            //从个位开始，逐位对所有数据进行基数排序
            for(int i=0;i<a.length;i++){
                //获取基数
                int mod=a[i]/(int)Math.pow(10,site)%10;
                if(buckets[mod]==null){
                    buckets[mod]=new LinkedList<Integer>();
                }
                buckets[mod].add(a[i]);
            }
            //判断是否是排序完成（）
            if(buckets[0]!=null&&buckets[0].size()==a.length){
                break;
            }
            site++;
            //如果排序未完成，将桶中数据按顺序更新到待排数组中，并清空桶中的数据
            int k=0;
            for(int i=0;i<10;i++){
                if(buckets[i]!=null){
                    for(int bucket:buckets[i]){
                        a[k++]=bucket;
                    }
                    buckets[i]=null;
                }
            }

        }
    }

    @Test
    public void testQuickSort(){
        System.out.println(Arrays.toString(arr));
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
