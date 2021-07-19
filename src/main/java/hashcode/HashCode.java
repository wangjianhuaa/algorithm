package hashcode;

import java.util.*;

/**
 * @author wangjianhua
 * @date 2021-05-13 10:04
 */
public class HashCode {
    /**
     * 计算
     * @param str
     * @param multiplier
     * @return
     */
    public static Integer hashCode(String str,Integer multiplier){
        int hash = 0;
        for (int i = 0; i <str.length() ; i++) {
            hash = multiplier*hash+str.charAt(i);
        }
        return hash;
    }

    /**
     * 计算hash碰撞概率
     */
    private static RateInfo hashCollisionRate(Integer multiplier, List<Integer> hashCodeList){
        int maxHash = hashCodeList.stream().max(Integer::compareTo).get();
        int minHash = hashCodeList.stream().min(Integer::compareTo).get();

        int collisionCount = (int)(hashCodeList.size()-hashCodeList.stream().distinct().count());
        double collisionRate = (collisionCount*1.0)/hashCodeList.size();
        return new RateInfo(maxHash,minHash,multiplier,collisionCount,collisionRate);
    }

    public static List<RateInfo> collisionRateList(Set<String> strList,Integer... multipliers){
        List<RateInfo> rateInfoList = new ArrayList<>();
        for (Integer multiplier : multipliers) {
            List<Integer> hashCodeList = new ArrayList<>();
            for (String str : strList) {
                Integer hashCode =hashCode(str,multiplier);
                hashCodeList.add(hashCode);
            }
            rateInfoList.add(hashCollisionRate(multiplier,hashCodeList));
        }
        return rateInfoList;
    }

    public static Map<Integer,Integer> hashArea(List<Integer> hashCodeList){
        Map<Integer,Integer> statistics = new LinkedHashMap<>();
        int start = 0;
        for (long i = 0x80000000; i <=0x7fffffff ; i+= 67108864) {
            long min = i;
            long max =min +67108864;
            //筛选出每个格子的哈希值数量
            int num = (int)hashCodeList.parallelStream().filter(x -> x>=min && x<max).count();
            statistics.put(start++,num);
        }
        return statistics;

    }

    public static Map<Integer,Integer> hashArea(Set<String> strList,Integer multiplier){
        List<Integer> hashCodeList = new ArrayList<>();
        for (String str : strList) {
            Integer hashCode =hashCode(str,multiplier);
            hashCodeList.add(hashCode);
        }
        return hashArea(hashCodeList);
    }
}
