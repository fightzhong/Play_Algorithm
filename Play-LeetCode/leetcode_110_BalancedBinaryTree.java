
public class leetcode_110_BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        boolean[] boo = new boolean[] {true};
        getDepth(root, boo);
        return boo[0];
    }
    
    /**
              获取当前根节点所在的深度, 并在深度差超过1的时候. 维护boo为false, 即不是平衡树
     */
    public int getDepth (TreeNode node, boolean[] boo) {
        if (node == null) {
            return 0;
        }
        
        int leftDepth = getDepth(node.left, boo);
        int rightDepth = getDepth(node.right, boo);
        if (Math.abs(leftDepth - rightDepth) > 1) {
            boo[0] = false;
        }
        
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
