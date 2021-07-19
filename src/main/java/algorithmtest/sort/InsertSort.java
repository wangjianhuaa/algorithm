package algorithmtest.sort;
/**
 * 直接插入排序法
 */
public class InsertSort {
    public static void main(String[] args) {
        int[]a = {3,4,1,5,2};
        insertSort(a);
    }
    public static void insertSort(int[] a){
        int t;
        for (int i = 1; i <a.length ; i++) {
            for (int j = i; j >0; j--) {
                if(a[j]<a[j-1]){
                 t=a[j-1];
                 a[j-1]=a[j];
                 a[j]=t;
                }
                else {
                    break;
                }
            }
        }

        //展示数组
        System.out.print("[");
        for (int i : a) {
            if(i==a.length){
                System.out.print(i+"]");
                return;
            }
                System.out.print(i + ",");
        }
    }
}
