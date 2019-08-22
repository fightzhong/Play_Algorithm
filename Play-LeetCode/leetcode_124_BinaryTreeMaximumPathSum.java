/**
 * 思路:
 *    <1> 如果一条路径是从左边到右边, 即经过了当前的根节点, 那么就不能往上返了, 往上返回的最大值必须
 *         是左边 + 根节点或者右边 + 根节点, 这样上一层才能拿到下一层左右两边的最大路径值
 *    <2> getMaxPathSum(TreeNode node)该方法是为了获取以node为根节点的最大路径和,
 *       其等于Max( 左边的最大路径 + 当前节点的值, 右边的最大路径 + 当前节点的值, 当前节点的值 )
 *       由于 ( 左边的最大路径 + 当前节点的值 + 右边的最大路径 )得出来的值不能与上面构成一条路径, 所以
 *       我们需要在成员变量位置维护一个max, 即结果, 然后在每一个根节点都要对max进行维护, 防止
 *       ( 左边的最大路径 + 当前节点的值 + 右边的最大路径 )这种情况能得到最大路径值被漏掉的情况
 */
public class leetcode_124_BinaryTreeMaximumPathSum {
	int max = Integer.MIN_VALUE;
	public int maxPathSum(TreeNode root) {
		Integer result = getMaxPathSum( root );

		return result == null ? 0 : max;
	}

	// 获取以node为根节点的最大路径和
	private Integer getMaxPathSum (TreeNode node) {
		if ( node == null )
			return null;

		Integer num1 = getMaxPathSum( node.left );
		Integer num2 = getMaxPathSum( node.right );

		if ( num1 == null && num2 == null ) {
			max = Math.max( max, node.val );
			return node.val;
		}

		// 如果左边为空
		if ( num1 == null ) {
			// 则最大路径为Max( node.val, node.val + num2 )
			int curMax = Math.max( node.val, node.val + num2 );
			// 此时需要对max进行维护一下max
			max = Math.max( max, curMax );
			return curMax;
		} else if ( num2 == null ) {
			int curMax = Math.max( node.val, node.val + num1 );
			max = Math.max( max, curMax );
			return curMax;
		}

		int m =  Math.max( num1 + node.val, num2 + node.val );
		m = Math.max( m, node.val );

		max = Math.max( max, Math.max( m, num1 + node.val + num2 ) );
		return m;
	}
}
