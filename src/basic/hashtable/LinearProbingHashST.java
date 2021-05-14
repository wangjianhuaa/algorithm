package basic.hashtable;

import basic.symboltable.UnorderedST;

/**
 * 线性探测法实现hashtable
 *
 * @author wangjianhua
 * @date 2021-05-14 18:02
 */
public class LinearProbingHashST<Key, Value> implements UnorderedST<Key, Value> {


    @SuppressWarnings("unchecked")
    public LinearProbingHashST(int cap) {
        keys = (Key[]) new Object[cap];
        values = (Value[]) new Object[cap];
    }

    private int N = 0;
    private int M = 16;
    private Key[] keys;
    private Value[] values;

    /**
     * 初始化容量
     */
    @SuppressWarnings("unchecked")
    private void init() {
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        /*散列函数实现  除留余数法
        除留余数法可以将整数散列到 [0, M-1] 之间，
        例如一个正整数 k，计算 k%M 既可得到一个 [0, M-1] 之间的 hash 值。
        注意 M 最好是一个素数，否则无法利用键包含的所有信息。
        例如 M 为 10k，那么只能利用键的后 k 位。
        对于其它数，可以将其转换成整数的形式，然后利用除留余数法。
        例如对于浮点数，可以将其的二进制形式转换成整数。
         */
        //屏蔽符号位 & 0x7fffffff
        //int为四个字节 首位是符号位 &0x7fffffff之后符号位固定为0  后面保持不变
        //所以0x7fffffff=01111111111111111111111111111111 不管符号位是正还是负都取正
        return (key.hashCode() & 0x7fffffff) % M;
    }

    @Override
    public int size() {
        return N;
    }

    /**
     * 查找
     */
    @Override
    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    /**
     * 插入
     */
    @Override
    public void put(Key key, Value value) {
        resize();
        putInternal(key, value);
    }

    private void putInternal(Key key, Value value) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    /**
     * 删除
     */
    @Override
    public void delete(Key key) {
        int i = hash(key);
        while (keys[i] != null && !key.equals(keys[i])) {
            i = (i + 1) % M;
        }

        //如果不存在 直接返回
        if (keys[i] == null) {
            return;
        }

        keys[i] = null;
        values[i] = null;
        //将之后相连的键值对重新插入
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valueToRedo = values[i];
            keys[i] = null;
            values[i] = null;
            N--;
            putInternal(keyToRedo, valueToRedo);
            i = (i + 1) % M;
        }
        N--;
        resize();
    }

    /**
     * α=N/M  把α成为使用率 理论证明 当α小于1/2时探测的预计次数只在1.5-2.5之间
     * 为了保证散列表的性能 应当调整数组的大小 使得α在[1/4,1/2] 之间
     */
    private void resize() {
        if (N >= M / 2) {
            resize(2 * M);
        } else if (N <= M / 8) {
            resize(M / 2);
        }
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<Key, Value>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.putInternal(keys[i], values[i]);
            }
        }
        keys = t.keys;
        values = t.values;
        M = t.M;
    }
}
