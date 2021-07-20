package jdk8;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/20/020 18:02
 */
public interface IFormula {
    /**
     * 平方
     * @param a 传参
     * @return 返回值
     */
    double calculate(int a);

    /**
     * 平方根
     * @param a int
     * @return  return
     */
    default double sqrt(int a){
        return Math.sqrt(a);
    }
}
