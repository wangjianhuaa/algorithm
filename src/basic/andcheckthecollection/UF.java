package basic.andcheckthecollection;

/**
 * 用于检查动态连通性问题，能动态连接两个点，并判断两个点是否联通
 * @author wangjianhua
 * @date 2021-05-08 15:16
 */
public abstract class UF {
    protected int [] id;

    /**
     * 构造器
     * @param N 数字
     */
    public UF(int N){
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    /**
     * 检测两个节点是否联通
     * @param p 数字p
     * @param q 数字q
     * @return boolean
     */
    public boolean connected(int p,int q){
        return find(p)==find(q);
    }

    public abstract int find(int p);

    public abstract void union(int p ,int q);
}
