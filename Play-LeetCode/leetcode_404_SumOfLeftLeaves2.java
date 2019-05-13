
public class leetcode_404_SumOfLeftLeaves2 {
    public int sumOfLeftLeaves(TreeNode root) {
        int[] sum = new int[] {0};
        
        traversal(root, sum);
        
        return sum[0];
    }
    
    /**
              对二叉树进行遍历, 找到叶子节点后更新sum的值
     */
    public void traversal (TreeNode node, int[] sum) {
        if (node == null) {
            return;
        }
        
        // 左叶子节点的标志
        if (node.left != null && node.left.left == null && node.left.right == null) {
            sum[0] += node.left.val;
        }
        
        traversal(node.left, sum);
        traversal(node.right, sum);
    }
}
