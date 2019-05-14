import java.util.ArrayList;

/**
     利用二分搜索树的性质, 中序遍历的结果一定是一个从小到大排列
 */
public class leetcode_098_ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        traversal( root, list );
        
        for ( int i = 1; i < list.size(); i ++ ) {
            if ( list.get(i) <= list.get(i - 1) ) {
                return false;
            }
        }
        
        return true;
    }  
    
    public void traversal (TreeNode node, ArrayList<Integer> list) {
        if ( node == null ) {
            return;
        }
        
        traversal( node.left, list );
        list.add( node.val );
        traversal( node.right, list );
    }
}