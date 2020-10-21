package algorithmtest.sort;

import java.util.Arrays;

/**
 * 快速排序法->最好的内部排序方法
 */
public class QuickSort {
    public static void main(String[] args) {
        int[]a = {6,8,3,2,7,10,9,4,5};
        quickSort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }

    public static void quickSort(int[] a,int left,int right){
        //i  j  左右哨兵
        int i,j,temp,t;
        if(left > right)
            return;
        //temp保存基准数
        temp = a[left];//此时第一次基准数为数组第一位 a[0]
        i=left;//0
        j=right;//a.length
        while(i!=j){
            while(a[j]>temp && i < j){
                j--;
            }
            while(a[i]<=temp && i < j){
                i++;
            }

            if(i<j){
                t = a[i];
                a[i] = a[j];
                a[j] = t;
            }

        }
        a[left] = a[i];
        a[i] = temp;
        //处理左边组的元素
        quickSort(a,left,i-1);
        //处理右边组的元素
        quickSort(a,i+1,right);
    }
}
