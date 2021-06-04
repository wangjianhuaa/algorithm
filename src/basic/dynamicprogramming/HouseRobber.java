package basic.dynamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 强盗抢劫问题
 * 抢劫一排住户，但是不能抢邻近的住户，求最大抢劫量。
 * 定义 dp 数组用来存储最大的抢劫量，其中 dp[i] 表示抢到第 i 个住户时的最大抢劫量。
 * 由于不能抢劫邻近住户，如果抢劫了第 i -1 个住户，那么就不能再抢劫第 i 个住户
 * @author wangjianhua
 * @date 2021/6/4/004 18:21
 */
public class HouseRobber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("输入住户数量：");
        int num = sc.nextInt();


        int [] nums = new int[num];
        Arrays.fill(nums, 1);
        int i =  rob(nums);

        System.out.println("抢劫数"+i);
    }
    public static int rob(int[] nums){

        int pre2 = 0,pre1 = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = Math.max(pre2+nums[i],pre1);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
