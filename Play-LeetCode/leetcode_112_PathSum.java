
public class leetcode_112_PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        boolean[] result = new boolean[] {false};
        
        sum(root, 0, sum, result);
        
        return result[0];
    }
    
    public void sum (TreeNode node, int lastSums, int targetSum, boolean[] result) {
        if (node == null) {
            return;
        }
        
        // 遍历到了叶子节点
        if (node.left == null && node.right == null) {
            if (lastSums + node.val == targetSum) {
                result[0] = true;
            }
            return;
        }
        
        sum(node.left, lastSums + node.val, targetSum, result);
        sum(node.right, lastSums + node.val, targetSum, result);
    }
}
