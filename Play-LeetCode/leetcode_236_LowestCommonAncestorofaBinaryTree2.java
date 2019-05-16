import java.util.ArrayList;

/**
      思路: 从根节点出发去查找root -> p 和 root -> q 的路径, 然后将路径中相同的节点的最后一个返回
              这里我采用一个对象Path去记录路径, 当找到了该节点的时候才创建Path, 并返回, 否则返回null,
                上一级获取到路径的时候会继续整合, 直到root节点
 
 */
public class leetcode_236_LowestCommonAncestorofaBinaryTree2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> commonPath = new ArrayList<TreeNode>();
        
        findNode( root, p, q, commonPath );
        return commonPath.get(0);
    }
    
    public boolean findNode (TreeNode node, TreeNode p, TreeNode q, ArrayList<TreeNode> commonPath) {
        if ( node == null ) {
            return false;
        }
        
        boolean b1 = findNode( node.left, p, q, commonPath );
        boolean b2 = findNode( node.right, p, q, commonPath );
        
        if ( ( b1 && b2 ) ) {
            System.out.println("================");
            commonPath.add(node);
            return true;
        }
        
        if ( b1 || b2 && ( node.val == p.val || node.val == q.val) ) {
            commonPath.add(node);
            return true;
        }
        
        return false;
    }
}
