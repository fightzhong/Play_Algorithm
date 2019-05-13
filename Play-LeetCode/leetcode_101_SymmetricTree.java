/**
      我们需要确定的是什么时候是对称的, 只有当两个元素都是相等的, 才是对称
      isMirror函数就是用来判断两个节点是否是对称的
 */
public class leetcode_101_SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        return isMirror(root.left, root.right);
    }
    
    public boolean isMirror (TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        
        if (t1 == null || t2 == null) {
            return false;
        }
        
        if (t1.val != t2.val) {
            return false;
        }
        
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }
}
