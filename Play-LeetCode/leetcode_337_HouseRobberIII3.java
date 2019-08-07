import java.util.HashMap;

/**
 * 动态规划: 偷以root为根节点的树, 求得偷取的最大的钱数
 *      
 *      分析: 偷取以node为根节点的树, 则可以选择偷node, 也可以选择不偷node
 *      f(node) 表示偷以node为根节点的树, 获得偷取的最大的钱数
 *      v(node)表示node所在的这个房子的钱数
 *      状态转移方程:  
 *                   - 偷node : v(node) + f(node.left.left) + f(node.left.right) + f(node.right.left) + f(node.right.right)
 *                   - 不偷node: f(node.left) + f(node.right)
 */
public class leetcode_337_HouseRobberIII3 {
    private Map<TreeNode, Integer> memo = new HashMap<>();
    public int rob(TreeNode root) {
        if ( root == null ) {
            return 0;
        }

        if ( memo.containsKey( root ) ) {
            return memo.get( root );
        }

        // 偷取以root为根节点的房子, 有两种情况, 偷取该房子, 不偷取该房子

        // 情况一: 偷取该房子 = 该房子的价值 + 该房子孙子节点的价值
        int leftSon = root.left == null ? 0 : rob( root.left.left ) + rob( root.left.right );
        int rightSon = root.right == null ? 0 : rob( root.right.left ) + rob( root.right.right );
        int val1 = leftSon + rightSon + root.val;

        // 情况二: 不偷取该房子
        int val2 = rob( root.left ) + rob( root.right );

        // 偷取以root为根节点的房子能获取的最大价值
        int res = Math.max( val1, val2 );
        memo.put( root, res );

        return memo.get( root );
    }
}

