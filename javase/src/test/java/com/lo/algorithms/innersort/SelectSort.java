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



    public void heapSort(int[] a){
        //创建大顶堆，堆化非叶节点；i表示非叶节点索引变化：a.length-a----->0
        //大顶堆是一个满二叉树，满二叉树序号可与全二叉树一一对应的二叉树(全二叉树所有节点都两个子节点)
        for(int i=(a.length-1)/2;i>=0;i--){
            sift(a,i,a.length-1);
        }
        //交换根梢（堆顶与无序区尾部交换），对根进行堆化。当剩下最后两个数据时，不进行堆化，直接比较大小
        for(int i=a.length-1;i>=2;i--){
            swap(a,0,i);
            sift(a,0,i-1);
        }
        swap(a,0,1);
    }

    /**堆化
     *
     * @param a
     * @param i 待堆化的堆顶索引。<br>
     * @param n 待堆化的堆尾索引。<br>
     */
    public static void sift(int[] a,int i,int n){
        //i与左右孩子最值比较，若i节点小，与最值孩子交换；i节点继续与新孩子比较，直到i节点比新孩子最值大或称为叶节点没有孩子为止。
        int temp=a[i];
        //j索引代表孩子节点的索引
        for(int j=i*2+1;j<=n;j=j*2+1){
            //比较两个孩子谁的比较大,j索引指向较大的
            if(j+1<=n&&a[j+1]>a[j]){
                j++;
            }
            //待堆化的堆顶比最值孩子还大的话，就不用堆化了，直接退出
            if(temp>=a[j]){
                break;
            }
            //待堆化的堆顶比最值孩子小的话，则堆顶值被最值孩子的值替换，堆顶指向最值孩子位置，进行下一步比较
            a[i]=a[j];
            i=j;
        }
        //原堆顶值赋值到i节点最后指向的位置
        a[i]=temp;

    }

    @Test
    public void testHeapSort(){
        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
