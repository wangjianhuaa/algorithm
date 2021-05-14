package basic.hashtable;

import java.util.Date;

/**
 * 自定义类型实现hashCode方法
 * @author wangjianhua
 * @date 2021-05-14 10:53
 */
public class Transaction {

    private final String who;

    private final Date when;

    private final double amount;

    public Transaction(String who, Date when, double amount) {
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    @Override
    public int hashCode(){
        int hash = 17;
        //31优质乘子
        int R = 31;
        hash = R*hash + who.hashCode();
        hash = R*hash+when.hashCode();
        hash = R*hash+((Double)amount).hashCode();
        return hash;
    }
}
