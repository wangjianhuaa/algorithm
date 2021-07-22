package jdk8.lambda;

import jdk8.IConverter;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangjianhua
 * @Description lambda表达式范围测试
 * @date 2021/7/21/021 11:18
 */
public class Lambda1 {

    /**
     * 静态变量
     */
    static int outerStaticNum;
    /**
     * 成员变量
     */
    int outerNum;

    public List<Object> testScopes(int from1, int from2){
        IConverter<Integer,String> iConverter = from ->{
            outerStaticNum = 323;
           return String.valueOf(outerStaticNum+from1);
        } ;

        IConverter<Integer,String> iConverter1 = from -> {
            outerNum=85;
            return String.valueOf(outerNum+from2);
        };
        return Arrays.asList(iConverter.convert(from1),iConverter1.convert(from2));


    }

}
