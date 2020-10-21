package algorithmtest.dp;

/**
 * 动态规划-->扔鸡蛋优化解
 */
public class ThrowEgg2 {
    public static int minSteps2 (int eggNum,int floorNum){
        if(eggNum <1 || floorNum <1){
            return 0;
        }
        //上一层备忘录，存储鸡蛋数量-1的floorNum层楼条件下的最优化尝试次数
        int [] precache = new int [floorNum+1];
        //当前层备忘录，存储当前鸡蛋数量的floorNum层楼条件下的最优化尝试次数
        int []currentCache = new int [floorNum+1];
        //把备忘录每个元素初始化成最大的尝试次数
        for (int i = 1; i <=floorNum; i++) {
          currentCache[i]=i;

        }
        for (int e = 2; e <= eggNum ; e++) {//鸡蛋
            //当前备忘录拷贝给上一次备忘录，并重新初始化当前备忘录
            precache = currentCache.clone();
            for(int b =1;b<=floorNum;b++){//楼层
                for (int x = 1; x <b ; x++) {
                    //F(b,e) = Min ( Max ( F(b-x,e)+1, F(x-1,e-1)+1 ) )
                    //扔鸡蛋的楼层从1到m都列举一遍，如果当时算出的尝试次数小于上一次算出的尝试次数，则取代上一次的尝试次数
                    currentCache[b] = Math.min(currentCache[b],1+Math.max(currentCache[b-x],precache[x-1]));
                }
                //System.out.println();
                //System.out.println(Arrays.toString(currentCache));
            }
        }
        return currentCache[floorNum];
    }
    public static void main(String[] args) {
        System.out.println(minSteps2(2, 1000));
    }
}
