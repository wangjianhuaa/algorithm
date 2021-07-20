package leetcode.divideandconquer;

import java.util.LinkedList;
import java.util.List;

/**
 * @author wangjianhua
 * @Description 不同的二叉搜索树
 * @date 2021/7/20/020 9:42
 */
public class UniqueBinarySearchTree {

    public static List<TreeNode> generateTrees(int n){
        if(n<1){
            return new LinkedList<TreeNode>();
        }
        return generateSubTrees(1,n);
    }


    private static List<TreeNode> generateSubTrees(int s,int e){
        List<TreeNode> res = new LinkedList<TreeNode>();
        if(s>e){
            res.add(null);
            return res;
        }
        for (int i = s; i <= e; ++i) {
            List<TreeNode> leftSubTrees = generateSubTrees(s, i - 1);
            List<TreeNode> rightSubTrees = generateSubTrees(i + 1, e);
            for (TreeNode left : leftSubTrees) {
                for (TreeNode right : rightSubTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }

        }
        return res;
    }

    public static void main(String[] args) {
        List<TreeNode> treeNodes = generateTrees(3);
        System.out.println(treeNodes);
    }
}
