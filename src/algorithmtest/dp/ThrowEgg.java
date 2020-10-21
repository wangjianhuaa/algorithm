package algorithmtest.dp;

/**
 * 动态规划--->扔鸡蛋
 */
public class ThrowEgg {
    public static int minSteps (int eggNum,int floorNum){
        if(eggNum <1 || floorNum <1){
            return 0;
        }
        //备忘录，存储eggNum个鸡蛋，floorNum层楼条件下的最优化尝试次数
        int [][] cache = new int [eggNum+1][floorNum+1];
        //把备忘录每个元素初始化成最大的尝试次数
        for (int i = 1; i <= eggNum; i++) {
            for (int j = 1; j <=floorNum ; j++) {
                cache[i][j]=j;
            }
            
        }
        for (int e = 2; e <= eggNum ; e++) {//鸡蛋
            for(int b =1;b<=floorNum;b++){//楼层
                for (int x = 1; x <b ; x++) {
                    //F(b,e) = Min ( Max ( F(b-x,e)+1, F(x-1,e-1)+1 ) )
                    //扔鸡蛋的楼层从1到m都列举一遍，如果当时算出的尝试次数小于上一次算出的尝试次数，则取代上一次的尝试次数
                    cache[e][b] = Math.min(cache[e][b],1+Math.max(cache[e][b-x],cache[e-1][x-1]));
                }

            }
        }
        for (int i = 0; i <cache.length ; i++) {
            for (int j = 0; j <cache[i].length ; j++) {
                //System.out.print(cache[i][j] + "");
            }
            //System.out.println();
        }

        return cache[eggNum][floorNum];

    }

    public static void main(String[] args) {
        
        System.out.println(minSteps(2, 100));
    }

}
