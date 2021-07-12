package algorithmtest.sort;

import java.util.Arrays;

public class HeapSort {
    /**
     * 选择排序--堆排序
     * @param array 待排序数组
     * @return 已排好数组
     */
    public static int[] heapSort(int[] array){
        //1.构建大顶堆 最后一个非叶子结点array.length/2-1
        for (int i = array.length/2-1; i >=0 ; i--) {
            //从第一个非叶子节点从下而上，从右到左调整结构
            adjustHeap(array,i,array.length);//调整 堆
        }
        //2.调整堆结构+交换堆顶元素与末尾元素，开始排序逻辑
        for (int j = array.length-1; j >0 ; j--) {
            //元素交换，作用是去掉大顶堆
            //把大顶堆的根元素放到数组的最后，就是每一次调整后就有一个元素确定位置
            swap(array,0,j);
            //元素交换后，最后一个元素已经确定位置，所以每次排序的都是去掉部分元素的数组的堆
            //从上而下，从左到右进行调整
            adjustHeap(array,0,j);
        }
        return  array;
    }

    /**
     * 调整大顶堆(仅是调整过程，建立在大顶堆已经建立好的基础上)
     * @param arr 待组堆
     * @param i 起始堆点
     * @param length 堆的长度
     */
    public static void adjustHeap(int[] arr,int i,int length){
        int temp = arr[i];//先取出当前元素i
        for (int k = i*2+1;k<length;k=k*2+1){//从i节点的左子节点开始，也就是2i+1处开始;k*2+1是继续找当前节点的子节点
            if(k+1<length && arr[k]<arr[k+1]){//如果左子节点小于右子节点，k指向右子节点
                k++;
            }
            if(arr[k]>temp){//如果子节点大于父节点，将子节点赋给父节点，此处不用交换
                arr[i]=arr[k];
                i=k;
            }else{
                break;
            }
        }
        arr[i]=temp;//将temp值放到最终的位置
    }

    /**
     * 交换元素
     * @param arr 数组
     * @param a 元素下标a
     * @param b 元素下标b
     */
    public static void swap(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {2,7,4,6,8,5,9};
        int[]arr1 = heapSort(arr);
        System.out.println(Arrays.toString(arr1));
    }
}
