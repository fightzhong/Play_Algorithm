import java.util.LinkedList;

/**
      我们需要确定的是什么时候是对称的, 只有当两个元素都是相等的, 才是对称
      isMirror函数就是用来判断两个节点是否是对称的
 */
public class leetcode_101_SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        return isMirror(root.left, root.right);
    }
    
    public boolean isMirror (TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        
        if (t1 == null || t2 == null) {
            return false;
        }
        
        if (t1.val != t2.val) {
            return false;
        }
        
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    // 非递归写法
    private class Solution2 {
        public boolean isSymmetric(TreeNode root) {
            if ( root == null ) return true;
            if ( root.left == null && root.right == null ) return true;
            if ( root.left == null || root.right == null ) return false;

            LinkedList<TreeNode> leftList = new LinkedList<>();
            LinkedList<TreeNode> rightList = new LinkedList<>();

            leftList.addLast( root.left );
            rightList.addLast( root.right );

            while ( !leftList.isEmpty() && !rightList.isEmpty() ) {
                TreeNode l = leftList.removeFirst();
                TreeNode r = rightList.removeFirst();

                // 如果两个都为null, 那么就判断下一个元素既可以了
                if ( l == null && r == null ) continue;
                if ( ( l == null && r != null ) || ( l != null && r == null ) ) return false;
                if ( l.val != r.val ) return false;

                // 在这里我们不管是不是null都给它推入进去
                leftList.add( l.left );
                leftList.add( l.right );

                rightList.add( r.right );
                rightList.add( r.left );
            }

            return leftList.isEmpty() && rightList.isEmpty();
        }
    }
}


