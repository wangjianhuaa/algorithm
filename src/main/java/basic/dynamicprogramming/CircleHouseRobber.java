package basic.dynamicprogramming;

/**
 * 强盗在环形街区抢劫
 * 动态规划
 * @author wangjianhua
 * @date 2021/6/10/010 18:27
 */
public class CircleHouseRobber {


    public static void main(String[] args) {

    }

    /**
     * 环形区域其实相对于横排 如果偶数 会少俩 奇数 会少一个抢劫
     * 因此 套用下方方法 思路还是保存原来的指针值  即可得出抢劫结果
     */
    public int rob(int[] nums){
        if(nums==null || nums.length==0){
            return 0;
        }
        int n = nums.length;
        if(n==1){
            return nums[0];
        }
        return Math.max(rob(nums,0,n-2),rob(nums,1,n-1));
    }

    private int rob(int[] nums,int first,int last){
        //还是用两个指针保存下一步结果
        int pre2 = 0,pre1 = 0;
        for (int i = first; i <= last; i++) {
            int cur = Math.max(pre1,pre2+nums[i]);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
