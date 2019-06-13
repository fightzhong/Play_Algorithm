/**
 * 这个版本能得出正确结果, 但是时间复杂度没有达到要求, 因为没有记录已经得到的信息, 从而多次递归同一个子树
 */
public class leetcode_337_HouseRobberIII {
    // 偷取当前节点, 或者偷取当前节点的孩子节点
    public int rob(TreeNode root) {
        if ( root == null ) {
            return 0;
        }

        // 偷取当前节点, 则下一步需要偷取当前节点的孙子节点或者孙子的孩子节点
        int cur = root.val + nextRob( root );
        
        /*  当前节点为root, 左孩子为left, 右孩子为right
         * .此时应该偷取左右孩子节点left, right, 但是!!不一定是同时偷取左右孩子, 有可能出现这种情况, 偷取左孩子，偷取右孩子的孩子节点,
         * .所以此时不能直接用nextRob来认为一定偷取了左右孩子, 而应该考虑偷取left, right，所以应该用rob函数, 这样才能考虑到上面的特殊情况 
         */ 
        int l = rob( root.left );
        int r = rob( root.right );
        
        return Math.max( l + r, cur );
    }
    
    // 偷取当前节点, 下一步应该考虑偷取考虑当前节点的孙子节点或者考虑偷取当前节点的孙子节点的孩子节点
    public int nextRob (TreeNode curNode) {
        int leftGrandson = 0;
        int rightGrandson = 0;
        if ( curNode.left != null ) {
            leftGrandson = rob( curNode.left.left ) + rob( curNode.left.right );
        }
        
        if ( curNode.right != null ) {
            rightGrandson = rob( curNode.right.left ) + rob( curNode.right.right );
        }
        
        return leftGrandson + rightGrandson;
    }
}

