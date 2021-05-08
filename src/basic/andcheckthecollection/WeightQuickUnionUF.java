package basic.andcheckthecollection;

/**
 * 解决quickUnion树高问题 加权
 * union操作会让较小的树连接在较大的树上面
 * 加权算法构造树深度 log N
 * @author wangjianhua
 * @date 2021-05-08 15:33
 */
public class WeightQuickUnionUF extends UF {
    /**
     * 保存节点的数量信息
     */
    private int [] sz;
    /**
     * 构造器
     *
     * @param N 数字
     */
    public WeightQuickUnionUF(int N) {
        super(N);
        this.sz = new int[N];
        for (int i = 0; i < N; i++) {
            this.sz[i]= 1;
        }
    }

    @Override
    public int find(int p) {
        while (p!=id[p]){
            p = id[p];
        }
        return p;
    }

    @Override
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if(sz[i]<sz[j]){
            id[i]= j;
            sz[j]+=sz[i];
        }
        else {
            id[j]=i;
            sz[i]+=sz[j];
        }
    }
}
