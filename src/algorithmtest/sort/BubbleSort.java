package algorithmtest.sort;

import java.util.Arrays;

/**
 * 冒泡排序法
 */
public class BubbleSort {
    public static void bubbleSort(int[] a){
      int t;
        for (int i = 0; i <a.length-1 ; i++) {
            for (int j = 0; j <a.length-1-i ; j++) {
              if(a[j]>a[j+1]){
               t = a[j];
               a[j]=a[j+1];
               a[j+1]=t;
              }
            }

        }
        System.out.println(Arrays.toString(a));
        Arrays.sort(a);
    }

    public static void main(String[] args) {
        int[] a = {9,8,7,6,5,4,3,2,1};
        bubbleSort(a);
    }
}
