
public class leetcode_104_MaximumDepthofBinaryTree {
    public int maxDepth(TreeNode root) {
        
        int[] depth = new int[] {0};
        traversal(root, depth, 1);
        
        return depth[0];
    }
    
    public void traversal (TreeNode node, int[] depth, int curDepth) {
        if (node == null) {
            return;
        }
        
        if (curDepth > depth[0]) {
            depth[0] = curDepth;
        }
        
        traversal(node.left, depth, curDepth + 1);
        traversal(node.right, depth, curDepth + 1);
    }
}
