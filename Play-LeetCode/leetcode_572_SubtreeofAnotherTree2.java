import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 思路:
 *    1、通过getSameNodes方法获取到s中所有与t.val相等的节点, 并放入ArrayList中
 *    2、对每一个list中的节点都与t判断是否是相同的结构, 判断的依据是judgeSubtree
 *       方法, 该方法的语义是判断s, t是否是同一个结构, 大概的逻辑如下:
 *       judgeSubtree(s, t) = judgeSubtree(s.left, t.left) && judgeSubtree(s.right, t.right)
 *                            && s.val == t.val
 *       即当其孩子节点均为相同结构并且两者的值也相等的情况下才是相同结构, 不过之间要考虑的因素很多, 就不细说
 *       了, 可以看代码, 上面的逻辑实现仅仅击败了10%, 优化的思路是, 由于可能存在多个相同的节点, 所以用哈希表
 *       对已经得出结果的节点进行记录, 减少重复的递归
 */
public class leetcode_572_SubtreeofAnotherTree2 {
	HashMap<TreeNode, Boolean> memo = new HashMap<>();

	public boolean isSubtree(TreeNode s, TreeNode t) {
		ArrayList<TreeNode> nodes = getSameNode( s, t );
		if ( nodes.size() == 0 ) return false;
		for ( TreeNode node: nodes ) {
			boolean result = judgeSubtree( node, t );
			if ( result )
				return result;
		}

		return false;
	}

	private ArrayList<TreeNode> getSameNode (TreeNode s, TreeNode t) {
		ArrayList<TreeNode> allSameNode = new ArrayList<>();

		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.addLast( s );

		while ( !queue.isEmpty() ) {
			TreeNode node = queue.removeFirst();
			if ( node.val == t.val ) {
				allSameNode.add( node );
			};

			if ( node.left != null ) queue.addLast( node.left );
			if ( node.right != null ) queue.addLast( node.right );
		}

		return allSameNode;
	}

	private boolean judgeSubtree (TreeNode s, TreeNode t) {
		if ( s == null && t == null ) return true;
		if ( s == null && t != null ) return false;
		if ( s != null && t == null ) return false;

		if ( memo.containsKey( s ) ) return memo.get( s );

		boolean b1 = judgeSubtree( s.left, t.left );
		boolean b2 = judgeSubtree( s.right, t.right );
		if ( s.val != t.val ) return false;
		if ( ( s.left == null && t.left != null ) || ( s.left != null && t.left == null ) ) return false;
		if ( ( s.right == null && t.right != null ) || ( s.right != null && t.right == null ) ) return false;
		if ( ( s.left != null && s.left.val != t.left.val )
				|| ( s.right != null && s.right.val != t.right.val ) ) return false;

		boolean result = b1 && b2;
		memo.put( s, result );

		return result;
	}
}
