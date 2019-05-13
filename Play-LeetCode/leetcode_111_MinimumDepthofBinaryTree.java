
public class leetcode_111_MinimumDepthofBinaryTree {
    public int minDepth(TreeNode root) {
        int[] depth = new int[] {Integer.MAX_VALUE};
        traversal(root, depth, 1);
        return depth[0] == Integer.MAX_VALUE ? 0 : depth[0];
    }
    
    public void traversal (TreeNode node, int[] depth, int curDepth) {
        if (node == null) {
            return;
        }
        
        if (node.left == null && node.right == null) {
            if (curDepth < depth[0]) {
                depth[0] = curDepth;
            }
        }
        
        traversal(node.left, depth, curDepth + 1);
        traversal(node.right, depth, curDepth + 1);
    }
}
