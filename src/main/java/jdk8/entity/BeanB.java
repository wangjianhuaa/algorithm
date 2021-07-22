package jdk8.entity;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/22/022 13:55
 */
public class BeanB {

    private String name;

    private Integer age;

    public BeanB(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "" + "" + name +
                "|" + age;
    }
}
