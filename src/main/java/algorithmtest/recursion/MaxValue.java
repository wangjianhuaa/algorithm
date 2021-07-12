package algorithmtest.recursion;

/**
 * 求出一个数组中的最大值
 */
public class MaxValue {

    public static void main(String[] args){
        int [] a = {5,8,6,4,9,6,3,12};
        //传统循环方法
        //int max = a[0];
        //for (int i = 1; i <a.length ; i++) {
        //    if(max < a[i]){
        //        max = a[i];
        //    }
        //}
        //System.out.println(max);
        //================================
        //遍历
        int result = m(a,0);
        System.out.println("result = " + result);
    }

    /**
     * 统计数组中的最大值->递归算法
     * @param a 数组
     * @return 最大值
     */
    public static int m(int[]a,int begin){
        int length = a.length-begin;
        if(length==1){
            return a[begin];
        }
            return Math.max(a[begin], m(a, begin + 1));
    }
}
