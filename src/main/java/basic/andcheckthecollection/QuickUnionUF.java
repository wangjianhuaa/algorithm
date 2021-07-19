package basic.andcheckthecollection;

/**
 * 可以快速进行union操作 只需要改变一个节点的union值即可
 * find操作开销很大 因为同一个连通分量的节点 id 值不同， find操作复杂度与树高有关
 * id 值只是用来指向另一个节点。因此需要一直向上查找操作，直到找到最上层的节点。
 * @author wangjianhua
 * @date 2021-05-08 15:28
 */
public class QuickUnionUF extends UF {
    /**
     * 构造器
     *
     * @param N 数字
     */
    public QuickUnionUF(int N) {
        super(N);
    }

    @Override
    public int find(int p) {
        while (p!=id[p]){
            p=id[p];
        }
        return p;
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot!=qRoot){
            id[pRoot]=qRoot;
        }
    }
}
