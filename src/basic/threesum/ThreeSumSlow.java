package basic.threesum;

/**
 * 最慢的for循环遍历方法
 * @author wangjianhua
 * @date 2021-05-19 18:05
 */
public class ThreeSumSlow implements ThreeSum {

    @Override
    public int count(int[] nums) {
        int N = nums.length;
        int t = 0;
        for (int i = 0; i <N ; i++) {
            for (int j = i+1; j <N ; j++) {
                for (int k = j+1; k <N ; k++) {
                    if(nums[i]+nums[j]+nums[k]==0){
                        t++;
                    }
                }
            }
        }
        return t;
    }
}
