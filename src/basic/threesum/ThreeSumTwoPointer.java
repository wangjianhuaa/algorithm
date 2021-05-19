package basic.threesum;

import java.util.Arrays;

/**
 * 双指针查找三数之和
 * 同样不适用于有重复元素的数组
 * @author wangjianhua
 * @date 2021-05-19 18:24
 */
public class ThreeSumTwoPointer implements ThreeSum {

    @Override
    public int count(int[] nums) {
        int N = nums.length;
        int t = 0;
        Arrays.sort(nums);
        for (int i = 0; i < N-2; i++) {
            int l = N+1,h = N-1,target = -nums[i];
            while (l<h){
                int sum = nums[l]+nums[h];
                if(sum == target){
                    t++;
                    l++;
                    h++;
                }
                else if(sum < target){
                    l++;
                }
                else {
                    l--;
                }
            }
        }
        return t;
    }
}
