/**
          思路: 由于是从小到大排列的元素, 所以可以从中间节点出发, 将中间节点作为树的根节点
          根节点的左节点指向[l, mid - 1]的节点形成的平衡二叉树
          根节点的右节点指向[mid + 1, r]的节点形成的平衡二叉树
          
      只有当l <= mid - 1 或者 mid + 1 <= r才说明还有元素, 此时还可以继续递归下去    
 */
public class leetcode_108_ConvertSortedArraytoBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        if ( nums.length == 0 ) {
            return null;
        }
        
        TreeNode root = sortedArrayToBST( nums, 0, nums.length - 1 );
        return root;
    }
    
    public TreeNode sortedArrayToBST (int[] nums, int l, int r) {
        int mid = l + ( r - l ) / 2;
        TreeNode cur = new TreeNode(nums[mid]);
        
        if ( l <= mid - 1 ) {
            cur.left = sortedArrayToBST( nums, l, mid - 1 );
        }
        
        if ( mid + 1 <= r ) {
            cur.right = sortedArrayToBST( nums, mid + 1, r );
        }
        
        return cur;
    }

}
