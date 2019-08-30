import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 思路:
 *    1、通过getSameNodes方法获取到s中所有与t.val相等的节点, 并放入ArrayList中
 *    2、对每一个相同的节点node, 判断与t是否是一样的结构, 判断的依据是对他们一起进行
 *       层次遍历, 如果两棵树层次遍历的每一个节点的值如果出现了一个不相等的, 就直接返回
 *       false, 其中需要判断null的情况, 我们需要把null也给压入队列, 但是在出队列的时候
 *       需要对null进行额外的处理
 * 
 */
public class leetcode_572_SubtreeofAnotherTree {
	public boolean isSubtree(TreeNode s, TreeNode t) {
		ArrayList<TreeNode> nodes = getSameNodes( s, t );
		if ( nodes.size() == 0 ) return false;
		for ( TreeNode node: nodes ) {
			boolean result = judgeSubtree( node, t );
			if ( result )
				return result;
		}

		return false;
	}

	/**
	 * 获取s中所有与t相等的节点
	 * @param s
	 * @param t
	 * @return
	 */
	private ArrayList<TreeNode> getSameNodes (TreeNode s, TreeNode t) {
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
		LinkedList<TreeNode> q1 = new LinkedList<>();
		LinkedList<TreeNode> q2 = new LinkedList<>();

		q1.addLast( s );
		q2.addLast( t );

		while ( !q1.isEmpty() && !q2.isEmpty() ) {
			TreeNode node1 = q1.removeFirst();
			TreeNode node2 = q2.removeFirst();

			// 对移除队列时节点为null的进行额外处理
			if ( node1 == null && node2 == null ) continue;
			if ( node1 == null && node2 != null ) return false;
			if ( node1 != null && node2 == null ) return false;
			if ( node1.val != node2.val ) return false;

			// 压入队列的时候无论是否是null都压入
			q1.addLast( node1.left );
			q1.addLast( node1.right );

			q2.addLast( node2.left );
			q2.addLast( node2.right );
		}

		return q1.isEmpty() && q2.isEmpty();
	}
}
