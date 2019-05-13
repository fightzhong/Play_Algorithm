
public class leetcode_404_SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null) {
                // 当左边的是左叶子节点的时候, 就应该返回左边叶子节点的值 + 对右边执行函数后返回的值
                return root.left.val + sumOfLeftLeaves(root.right);
            }
        }
        
        int leftSum = sumOfLeftLeaves(root.left);
        int rightSum = sumOfLeftLeaves(root.right);
        
        return leftSum + rightSum;
    }
}
