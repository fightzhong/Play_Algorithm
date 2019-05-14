/**
      找到要删除的元素后, 将删除元素的左边拼接到右边元素的最小元素的左边
 
 */
public class leetcode_450_DeleteNodeinaBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if ( root == null ) {
            return null;
        }
        
        if ( root.val > key ) {
            root.left = deleteNode( root.left, key );
        } else if ( root.val < key ) {
            root.right = deleteNode( root.right, key );
        } else {
            // 找到了要删除的节点
            if ( root.right == null ) {
                return root.left;
            } else if ( root.left == null ) {
                return root.right;
            }
            
            TreeNode minNode = findMin( root.right );
            minNode.left = root.left;
            return root.right;
        }
        
        return root;
    }
    
    /**在以node为根节点的子树中查找最小的元素**/
    private TreeNode findMin (TreeNode node) {
        while ( node.left != null ) {
            node = node.left;
        }
        
        return node;
    }
}
