
public class leetcode_222_CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
       int[] count = new int[] {0};
        count(root, count);
        return count[0];
    }
    
    public void count (TreeNode node, int[] count) {
        if (node == null) {
            return;
        }
        
        count[0] ++; 
        count(node.left, count);
        count(node.right, count);
    }
}
