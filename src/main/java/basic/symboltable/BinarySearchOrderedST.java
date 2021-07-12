package basic.symboltable;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找实现有序符号表
 * @author wangjianhua
 * @date 2021-05-10 17:24
 */
@SuppressWarnings("unchecked")
public class BinarySearchOrderedST<Key extends Comparable<Key>,Value> implements OrderedST<Key,Value>{


    /*
    使用一对平行数组 一个存储键 一个存储值
    二分查找的rank()方法至关重要 当键在表中时，它能知道键在表中的位置
    当键不在表中时 它也知道该在何处插入新键
    二分查找最多需要log N+1 次比较 使用二分查找实现的符号表的查找操作
    所需要的时间最多是对数级别的 但是插入操作需要移动数组元素
    是线性级别的
     */

    private Key[] keys;

    private Value[] values;

    private int N = 0;

    /**
     * 构造器初始化符号表容量
     * @param capacity 容量
     */
    public BinarySearchOrderedST(int capacity){
        keys = (Key[])new Comparable[capacity];
        values = (Value[]) new Comparable[capacity];
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public void put(Key key, Value value) {
        int index = rank(key);
        //如果找到已经存在的节点键为key 就更新这个键值对的value为传入的value
        if(index<N && keys[index].compareTo(key)==0){
            values[index] = value;
            return;
        }
        //否则在数组中插入新的节点 需要先将插入元素之后的元素都移动一个位置
        for(int j = N;j>index;j--){
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }
        keys[index] = key;
        values[index] = value;
        N++;


    }

    @Override
    public Value get(Key key) {
        int index = rank(key);
        if(index < N && keys[index].compareTo(key)==0){
            return values[index];
        }
        return null;
    }

    @Override
    public Key min() {
        return keys[0];
    }

    @Override
    public Key max() {
        return keys[N-1];
    }

    @Override
    public int rank(Key key) {
        int l = 0,h = N-1;
        while (l<=h){
            int m = l + (h-1)/2;
            int cmp = key.compareTo(keys[m]);
            if(cmp==0){
                return m;
            }
            else if(cmp<0){
                h = m-1;
            }
            else {
                l = m+1;
            }
        }
        return l;
    }

    @Override
    public List<Key> keys(Key l, Key h) {
        int index = rank(l);
        List<Key> list= new ArrayList<>();
        while (keys[index].compareTo(h)<0){
            list.add(keys[index]);
            index++;
        }
        return list;
    }
}
