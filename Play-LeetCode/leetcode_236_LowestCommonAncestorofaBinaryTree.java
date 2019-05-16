/**
      思路: 从根节点出发去查找root -> p 和 root -> q 的路径, 然后将路径中相同的节点的最后一个返回
              这里我采用一个对象Path去记录路径, 当找到了该节点的时候才创建Path, 并返回, 否则返回null,
                上一级获取到路径的时候会继续整合, 直到root节点
 
 */
public class leetcode_236_LowestCommonAncestorofaBinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        Path p1 = findPath( root, p );
        Path q1 = findPath( root, q );
        
        while ( ( p1.next != null && q1.next != null && p1.next.node.val == q1.next.node.val ) ) {
            p1 = p1.next;
            q1 = q1.next;
        }
        
        return p1.node;
    }
    
    public Path findPath (TreeNode node, TreeNode target) {
        if (node == null) {
            return null;
        }

        if ( node.val == target.val ) {
            return new Path( node, null );
        }
        
        Path p1 = findPath( node.left, target );
        Path p2 = findPath( node.right, target );
        
        if ( p1 == null && p2 == null ) {
            return null;
        }
        
        return p1 == null ? new Path( node, p2 ) : new Path( node, p1 );
    }
    
    class Path {
        TreeNode node;
        Path next;
        
        public Path(TreeNode node, Path next) {
            this.node = node;
            this.next = next;
        }
    }
    
}
