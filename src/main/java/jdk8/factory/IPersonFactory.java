package jdk8.factory;

import jdk8.entity.Person;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/21/021 10:59
 */
@FunctionalInterface
public interface IPersonFactory<P extends Person> {
    /**
     * 创建接口
     * @param name 名
     * @param phone 电话
     * @return P
     */
    P create(String name,String phone);
}
