package basic.dynamicprogramming;

/**
 * leetcode爬楼梯问题
 * 有 N 阶楼梯，每次可以上一阶或者两阶，求有多少种上楼梯的方法。
 * @author wangjianhua
 * @date 2021/5/26/026 18:17
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        //1
        System.out.println(climbStairs(1));
        //11    2
        System.out.println(climbStairs(2));
        //111  12  21
        System.out.println(climbStairs(3));
        //112 22 1111 121 211
        System.out.println(climbStairs(4));
        //1112 122 212 1121 221 11111 1211 2111
        System.out.println(climbStairs(5));
    }


    public static int climbStairs(int n){
        //如果小于等于2时 直接返回
        if(n<=2){
           return n;
        }
        //否则循环加一下 因为第 i 个楼梯可以从第 i-1 和 i-2 个楼梯再走一步到达
        // ，走到第 i 个楼梯的方法数为走到第 i-1 和第 i-2 个楼梯的方法数之和。
        //该算法思想是保存上一次的结果 然后简化计算步骤
        int pre2 = 1,pre1 = 2;
        for (int i = 2; i < n; i++) {
            int cur = pre1+pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
