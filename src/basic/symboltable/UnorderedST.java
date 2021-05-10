package basic.symboltable;

/**
 * 无序符号表接口
 * @author wangjianhua
 * @date 2021-05-08 15:44
 */
public interface UnorderedST<Key,Value> {
    /**
     * 大小
     * @return 大小
     */
    int size();

    /**
     * 通过key获取value
     * @param key key
     * @return value
     */
    Value get(Key key);

    /**
     * 放入键值对
     * @param key key
     * @param value value
     */
    void put(Key key,Value value);

    /**
     * 删除
     * @param key key
     */
    void delete(Key key);
}
