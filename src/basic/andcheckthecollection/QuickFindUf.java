package basic.andcheckthecollection;

/**
 * QuickFind
 * 快速进行find操作也就是可以快速判断两个节点是否连通。
 * 需要保证同一连通分量的所有节点的 id 值相等，
 * 就可以通过判断两个节点的 id 值是否相等从而判断其连通性。
 * 但是 union 操作代价却很高，
 * 需要将其中一个连通分量中的所有节点 id 值都修改为另一个节点的 id 值。
 * @author wangjianhua
 * @date 2021-05-08 15:23
 */
public class QuickFindUf extends UF {
    /**
     * 构造器
     *
     * @param N 数字
     */
    public QuickFindUf(int N) {
        super(N);
    }

    @Override
    public int find(int p) {
        return id[p];
    }

    @Override
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if(pId==qId){
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if(id[i]==pId){
                id[i]=qId;
            }
        }
    }
}
