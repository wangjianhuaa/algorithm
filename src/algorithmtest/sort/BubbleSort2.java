package algorithmtest.sort;

import java.util.Arrays;

/**
 * 快速排序法
 */
public class BubbleSort2 {

    public static void main(String[] args) {
        int[]a = {3,4,1,5,2};
        bubbleSort(a);
    }
    public static void bubbleSort(int[]a){
        int min,t;
        for (int i = 0; i <a.length-1 ; i++) {
            min = i;
            for (int j = i+1; j <a.length ; j++) {
                if(a[j]<a[min]){
                    min = j;
                }
            }
            if(i!=min){
                t=a[min];
                a[min]=a[i];
                a[i] = t;
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
