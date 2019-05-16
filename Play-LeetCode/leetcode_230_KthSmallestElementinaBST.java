import java.util.ArrayList;

/**
      通过中序遍历的结果是一个从小到大的数集, 将这个数集放入数组中就可以直接获取第K小的元素了
 */
public class leetcode_230_KthSmallestElementinaBST {
    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> vals = new ArrayList<Integer>();
        inOrderTraversal( root, vals );
        return vals.get( k - 1 );
    }
    
    public void inOrderTraversal (TreeNode node, ArrayList<Integer> vals) {
        if ( node == null ) {
            return;
        }
        
        inOrderTraversal( node.left, vals );
        vals.add( node.val );
        inOrderTraversal( node.right, vals );
    }
}
