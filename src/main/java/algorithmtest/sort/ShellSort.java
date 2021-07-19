package algorithmtest.sort;

import java.util.Arrays;

/**
 * 希尔排序法
 */
public class ShellSort {

    public static void shellSort(int[] a) {
        int j = 0;//下标
        int t = 0;//元素
        //分组
        for (int increment = a.length/2; increment>0;increment/=2) {
            //每个组内排序
            for (int i = increment; i <a.length ; i++) {
                //组内的插入排序
                t=a[i];
                for ( j = i; j >=increment; j-=increment) {
                    if(t<a[j-increment]){
                        a[j]=a[j-increment];
                    }
                    else{
                        break;
                    }
                }
                a[j]=t;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {3,5,2,9,4,1,8,7,6};
        shellSort(a);
        System.out.println(Arrays.toString(a));
    }
}
