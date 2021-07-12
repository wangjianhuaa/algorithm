package algorithmtest.recursion;



public class FboNaQie {
    public static void main(String[] args) {
        //long l1 = System.currentTimeMillis();
        //System.out.println(fbi(1000000000));
        //long l2 = System.currentTimeMillis();
        //System.out.println(l2-l1);
        //long l3 = System.currentTimeMillis();
        //fbi2(1000000000);
        //long l4 = System.currentTimeMillis();
        //System.out.println(l4-l3);
        System.out.println(fbi(50));


    }

    /**
     * 存储之前的俩个状态 空间复杂度O(1)
     * @param n n为想要的和的数字
     * @return 斐波那契的前n项和
     */
    public static long fbi(long n){
       if(n==1 || n==2)
           return 1;
        long prev = 1, curr = 1;
        for (long i = 3; i <=n ; i++) {
            long sum = prev + curr;
            prev  = curr;
            curr  = sum ;
        }
        return curr;
    }
    //传统解法
    public static void fbi2(long n){
        long a = 1,b = 1,c =2;
        for (long i = 0; i <n-1 ; i++) {
           a = b;
           b = c;
           c = a + b;
        }
        System.out.println(a);
    }
    //暴力解法
    public static int fbi1(int n){
        if(n==1 || n==2)
         return 1;
        else
            return fbi1(n-2)+fbi1(n-1);
    }
}
