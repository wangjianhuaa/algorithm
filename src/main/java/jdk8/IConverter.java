package jdk8;

/**
 * @author wangjianhua
 * @Description 函数式接口
 * @date 2021/7/20/020 18:26
 */
@FunctionalInterface
public interface IConverter<F,T> {

    /**
     * 转换
     * @param from 要转换的
     * @return 转换后的
     */
    T convert(F from);
}
