public class leetcode_100_SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        
        boolean[] boo = new boolean[] {true};
        tranversal(p, q, boo);
        
        return boo[0];
    }
    
    public void tranversal (TreeNode p, TreeNode q, boolean[] boo) {
        if (p == null && q == null) {
            return;
        }
        
        if ( (p == null && q != null) || (p != null && q == null) || (p.val != q.val) ) {
            boo[0] = false;
            return;
        }
        
        tranversal(p.left, q.left, boo);
        tranversal(p.right, q.right, boo);
    }
}
