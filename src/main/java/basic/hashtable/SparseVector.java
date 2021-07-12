package basic.hashtable;

import java.util.HashMap;

/**
 * 稀疏向量乘法
 * @author wangjianhua
 * @date 2021-05-14 18:02
 */
public class SparseVector {
    /*
        稀疏向量乘法用于降低向量计算复杂度 当向量为稀疏向量时
        可以使用符号表来存储向量中的非0索引和值
        使得乘法运算只需要对那些非0元素进行即可
     */
    /**
     * 初始化hashMap用于存储向量
     */
    private HashMap<Integer,Double> hashMap;

    public SparseVector(double[] vector){
        hashMap = new HashMap<>();
        for (int i = 0; i < vector.length; i++) {
            if(vector[i]!=0){
                hashMap.put(i,vector[i]);
            }
        }
    }

    public double get(int i){
        return hashMap.getOrDefault(i,0.0);
    }

    public double dot(SparseVector other){
        double sum = 0;
        for (int i  : hashMap.keySet()) {
            sum += this.get(i) * other.get(i);
        }
        return sum;
    }

}
