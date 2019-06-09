import java.util.HashMap;

public class leetcode_337_HouseRobberIII {
    public static HashMap<TreeNode, Integer> hasRob = new HashMap<>();
    public int rob(TreeNode root) {
        // 从根节点开始偷
        int max1 = robMoney( root );
        
        System.out.println(max1);
        // 从根节点的左右孩子开始偷
        int max2 = 0;
        if ( root != null ) {
            max2 = robMoney( root.left ) + robMoney( root.right );
            if ( root.right != null ) {
                int temp = robMoney( root.left ) + robMoney( root.right.left ) + robMoney( root.right.right );
                max2 = Math.max( max2, temp );
            }
            
            if ( root.left != null ) {
                int temp = robMoney( root.right ) + robMoney( root.left.left ) + robMoney( root.left.right );
                max2 = Math.max( max2, temp );
            }
        }
        
        return Math.max( max1, max2 );
    }
    
    public int robMoney (TreeNode curNode) {
        if ( curNode == null ) {
            return 0;
        }
        
        // 如果偷取了当前节点, 那么可以从当前节点的孙子节点开始再偷, 或者从当前节点的孙子节点的孩子节点开始偷
        int max1 = 0;
        int max2 = 0;
        
        /*************************从当前节点的孙子节点开始偷****************************/ 
        if ( curNode.left != null ) {
            int leftMax = hasRob.containsKey( curNode.left.left ) ? hasRob.get( curNode.left.left ) : robMoney( curNode.left.left );
            int rightMax = hasRob.containsKey( curNode.left.right ) ? hasRob.get( curNode.left.right ) : robMoney( curNode.left.right );
            max1 = leftMax + rightMax;
            if ( curNode.right != null ) {
                max1 += robMoney( curNode.right.left ) + robMoney( curNode.right.right );
            }
        }
        
        if ( curNode.right != null ) {
            int leftMax = hasRob.containsKey( curNode.right.left ) ? hasRob.get( curNode.right.left ) : robMoney( curNode.right.left );
            int rightMax = hasRob.containsKey( curNode.right.right ) ? hasRob.get( curNode.right.right ) : robMoney( curNode.right.right );
            max2 = leftMax + rightMax;
            if ( curNode.left != null ) {
                max2 += robMoney( curNode.left.left ) + robMoney( curNode.left.right );
            }
        }
        
        int grandsonMax = Math.max( max1, max2 );
        
        /*************************从当前节点的孙子节点的孩子节点开始偷****************************/ 
        int m1 = 0;
        int m2 = 0;
        int m3 = 0;
        int m4 = 0;
        
        if ( curNode.left != null && curNode.left.left != null ) {
            int leftMax = hasRob.containsKey( curNode.left.left.left ) ? hasRob.get( curNode.left.left.left ) : robMoney( curNode.left.left.left );
            int rightMax = hasRob.containsKey( curNode.left.left.right ) ? hasRob.get( curNode.left.left.right ) : robMoney( curNode.left.left.right );
            m1 = leftMax + rightMax;
            
            if ( curNode.right != null ) {
                m1 += rob( curNode.left.right ) + robMoney( curNode.right.left ) + robMoney( curNode.right.right );
            }
        }
        
        if ( curNode.left != null && curNode.left.right != null ) {
            int leftMax = hasRob.containsKey( curNode.left.right.left ) ? hasRob.get( curNode.left.right.left ) : robMoney( curNode.left.right.left );
            int rightMax = hasRob.containsKey( curNode.left.right.right ) ? hasRob.get( curNode.left.right.right ) : robMoney( curNode.left.right.right );
            m2 = leftMax + rightMax;
            if ( curNode.right != null ) {
                m2 += rob( curNode.left.left ) + robMoney( curNode.right.left ) + robMoney( curNode.right.right );
            }
        }
        
        if ( curNode.right != null && curNode.right.left != null ) {
            int leftMax = hasRob.containsKey( curNode.right.left.left ) ? hasRob.get( curNode.right.left.left ) : robMoney( curNode.right.left.left );
            int rightMax = hasRob.containsKey( curNode.right.left.right ) ? hasRob.get( curNode.right.left.right ) : robMoney( curNode.right.left.right );
            m3 = leftMax + rightMax;
            if ( curNode.left != null ) {
                m3 += rob( curNode.right.right ) + robMoney( curNode.left.left ) + robMoney( curNode.left.right );
            }
        }
        
        if ( curNode.right != null && curNode.right.right != null ) {
            int leftMax = hasRob.containsKey( curNode.right.right.left ) ? hasRob.get( curNode.right.right.left ) : robMoney( curNode.right.right.left );
            int rightMax = hasRob.containsKey( curNode.right.right.right ) ? hasRob.get( curNode.right.right.right ) : robMoney( curNode.right.right.right );
            m4 = leftMax + rightMax;
            if ( curNode.left != null ) {
                m4 += rob( curNode.right.left ) + robMoney( curNode.left.left ) + robMoney( curNode.left.right );
            }
        }
        int grandsonSonMax = Math.max( Math.max(m1, m2), Math.max(m3, m4) );
        
        int max = curNode.val + Math.max( grandsonMax, grandsonSonMax );
        hasRob.put( curNode, max );
        return max;
    }
}

