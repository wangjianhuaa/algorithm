package basic.symboltable;

import java.util.List;

/**
 * 有序符号表接口
 * @author wangjianhua
 * @date 2021-05-08 16:00
 */
public interface OrderedST<Key extends Comparable<Key>,Value> {
    /**
     * 数量
     * @return 数量
     */
    int size();

    /**
     * 放入键值对
     * @param key key
     * @param value value
     */
    void put(Key key,Value value);

    /**
     * 获得value
     * @param key key
     * @return value
     */
    Value get(Key key);

    /**
     * 最小的key
     * @return key
     */
    Key min();

    /**
     * 最大的key
     * @return key
     */
    Key max();

    int rank(Key key);

    /**
     * key集合
     * @param l key
     * @param h key
     * @return 集合
     */
    List<Key> keys(Key l,Key h);
}
