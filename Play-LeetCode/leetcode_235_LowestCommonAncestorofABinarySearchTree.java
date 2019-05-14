import java.util.ArrayList;
/**
      两个节点的最小公共祖先节点: 那么我们可以通过查找这两个元素从root到目标元素的路径, 则
          在路径中最后一个相同的元素就是最小的祖先元素
 */
public class leetcode_235_LowestCommonAncestorofABinarySearchTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        ArrayList<TreeNode> path1 = getPath(root, p);
        ArrayList<TreeNode> path2 = getPath(root, q);
        TreeNode commonAncestor = null;
        int len = path1.size() > path2.size() ? path2.size() : path1.size();
        for (int i = 0; i < len; i ++) {
            if (path1.get(i) != path2.get(i)) {
                break;
            }
            
            commonAncestor = path1.get(i);
        }
        
        return commonAncestor;
    }
    
    /**在以root为根节点的二叉树中查找从root到node经过的节点**/
    public ArrayList<TreeNode> getPath (TreeNode root, TreeNode node) {
        ArrayList<TreeNode> path = new ArrayList<TreeNode>();
        while (root != null) {
            path.add(root);
            if (root.val < node.val) {
                root = root.right;
            } else if (root.val > node.val) {
                root = root.left;
            } else {
                break;
            }
        }
        
        return path;
    }
}
