package basic.bst;

/**
 * 红黑树 实现
 * @author wangjianhua
 * @date 2021-05-12 18:23
 */
public class RedBlackBST<Key extends Comparable<Key>,Value> extends BST<Key,Value> {


    /*
        红黑树是2-3查找树，但他不需要分别定义2-节点和3-节点，
        而是在普通的二叉查找树之上，为节点添加颜色。
        指向一个节点的链接颜色如果是红色，那么这个节点和上层节点
        表示的是一个3-节点，而黑色则是普通链接
        <=============================>
        红黑树特质 红链接都为左链接
        完美黑色平衡，即任意空链接到根节点的路径上的黑链接数量相同
        一颗大小为N的红黑树 它的高度不会超过2logN
        最坏的情况是它所对应的2-3树 构成最左边的路径节点
        全都是3-节点而其余都是2-节点
        红黑树大多数的操作都是对数级别的
     */

    private static final boolean RED = true;

    private static final boolean BLACK = false;

    private boolean isRed(Node x){
        if(x==null){
            return false;
        }
        return x.color==RED;
    }

    /**
     * 左旋
     * 合法的右链接都应该为黑色
     * 如果出现红色 就需要进行左旋转操作
     */
    public Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        recalculateSize(h);
        return x;
    }

    /**
     * 右旋
     * 右旋转是为了转换两个连续的左红链接
     */
    public Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        recalculateSize(h);
        return x;
    }

    /**
     * 颜色转换
     * 将根节点颜色置红色 叶子结点颜色置为黑色
     */
    public void flipColors(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root,key,value);
        root.color = BLACK;
    }

    private Node put(Node x,Key key,Value value){
        if(x==null){
            Node node = new Node(key,value,1);
            node.color = RED;
           return node;
        }
        int cmp = key.compareTo(x.key);
        if(cmp ==0){
            x.val = value;
        }
        else if(cmp < 0){
            x.left  = put(x.left,key,value);
        }
        else {
            x.right = put(x.right,key,value);
        }
        //接下来进行颜色判断并进行相应旋转操作
        //如果右子节点为红色 且左子节点为黑色 左旋
        if(isRed(x.right) && !isRed(x.left)){
            x = rotateLeft(x);
        }
        //如果左子节点为红色且左子节点的左子节点为红色 右旋
        if(isRed(x.left) && isRed(x.left.left)){
            x = rotateRight(x);
        }
        //如果左右子节点都为红色 颜色转换
        if(isRed(x.left) && isRed(x.right)){
            flipColors(x);
        }
        /*
        根节点一定为黑色 因为根节点没有上层节点
        也就没有上层节点的左链接指向根节点
        flipColors 有可能会使根节点的颜色变为红色
        每当根节点由红色变为黑色时 树的黑链接高度加1
         */
        recalculateSize(x);
        return x;
    }
}
