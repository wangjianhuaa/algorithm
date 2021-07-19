package algorithmtest.sort;

import java.util.Arrays;

public class MergeSort {
    /**
     * 归并排序法
     * @param a     需要排序的数组
     * @param first 开始下标
     * @param last  结束下标
     * @param temp  归并时临时存放数据的数组 需要长度与a相等
     */
    public static void mergeSort(int[] a, int first, int last, int[] temp) {
        if(first<last){
            int middle = (first+last)/2;
            mergeSort(a,first,middle,temp);//左半部分排序
            mergeSort(a,middle+1,last,temp);//右半部分排好序
            mergeArray(a,first,middle,last,temp);//合并左右部分

        }
    }
    //将两个序列合并 ->  a[first--middle] ,a[middle+1--last]
    public static void mergeArray(int[] a ,int first,int middle,int end,int [] temp){
        //第一个序列的开始和结束下标
        int i = first;
        int m = middle;
        //第二个序列的开始和结束下标
        int j = middle+1;
        int n = end;
        //操作temp数组的下标
        int k = 0;
        while(i <= m && j<=n){
            if(a[i] <= a[j]){
                temp[k] = a[i];
                k++;
                i++;
            }
            else{
                temp[k] = a[j];
                k++;
                j++;
            }
        }
        //当一个序列全部放入temp数组后，将另一个序列剩余的元素加入temp
        while(i<=m){
            temp[k] = a[i];
            k++;
            i++;
        }
        while(j<=n){
            temp[k] = a[j];
            k++;
            j++;
        }
        //将完成排序的temp数组赋值到a数组中
        for (int ii = 0; ii <k ; ii++) {
            a[first+ii]=temp[ii];
        }

    }

    public static void main(String[] args) {
        int[] arr = {8, 7, 6, 5};
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
        System.out.println(Arrays.toString(arr));
    }
}
