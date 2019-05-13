
public class leetcode_226_InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);

        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        
        return root;
    }
}
