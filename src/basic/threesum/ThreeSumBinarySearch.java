package basic.threesum;

import java.util.Arrays;

/**
 * 二分查找三数之和
 * 适用于数组中无重复的元素
 * @author wangjianhua
 * @date 2021-05-19 18:10
 */
public class ThreeSumBinarySearch implements ThreeSum {

    @Override
    public int count(int[] nums) {
        Arrays.sort(nums);
        int N = nums.length;
        int t = 0;
        for (int i = 0; i <N ; i++) {
            for (int j = 0; j <N ; j++) {
                int target = -nums[i]-nums[j];
                int index = BinarySearch.search(nums,target);
                //下标应该大于j 否则会重复统计
                if(index>j){
                    t++;
                }
            }
        }
        return t;
    }
}
