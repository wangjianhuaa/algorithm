package basic.threesum;

/**
 * 简单实现一个二分查找
 * @author wangjianhua
 * @date 2021-05-19 18:11
 */
public class BinarySearch {

    public static int search(int[] nums,int target){
        int l =  0, h = nums.length-1;
        while (l<=h){
            int m = l+(h-1)/2;
            if(target==nums[m]){
                return m;
            }
            else if(target>nums[m]){
                l = m+1;
            }
            else {
                h= m-1;
            }
        }
        return -1;
    }
}
