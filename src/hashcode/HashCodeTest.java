package hashcode;

import java.util.List;
import java.util.Set;

/**
 * @author wangjianhua
 * @date 2021-05-13 11:04
 */
public class HashCodeTest {



    public static void main(String[] args) {
        Set<String> words =read();
        testCollisionRate(words);
        testHashArea(words);
    }

    public static  Set<String> read(){
        //读取文件
        return FileUtil.readWordSet("D:\\work\\workdemo\\ algorithm\\src\\hashcode\\103976个英语单词库.txt");
    }
    /**
        可以看出199时碰撞概率很小 但是有个问题 用199做为乘数 又返回int值 它的范围值远超过了int的取值范围 会丢失数据信息
    */
    public static void testCollisionRate(Set<String> words){
        System.out.println("单次数量:"+words.size());
        List<RateInfo> rateInfoList = HashCode.collisionRateList(words,2, 3, 5, 7, 17, 31, 32, 33, 39, 41, 199);
        for (RateInfo rateInfo : rateInfoList) {
            System.out.println(String.format("乘数 = %4d, 最小hash = %11d, 最大hash =%10d, 碰撞数量 = %6d," +
                    "碰撞概率 = %.4f%% ",rateInfo.getMultiplier(),rateInfo.getMinHash(),rateInfo.getMaxHash()
                    ,rateInfo.getCollisionCount(),rateInfo.getCollisionRate()*100));
        }
    }

    public static void testHashArea(Set<String> words){
        System.out.println(HashCode.hashArea(words,2).values());
        System.out.println(HashCode.hashArea(words,7).values());
        System.out.println(HashCode.hashArea(words,31).values());
        System.out.println(HashCode.hashArea(words,32).values());
        System.out.println(HashCode.hashArea(words,199).values());
    }
}
