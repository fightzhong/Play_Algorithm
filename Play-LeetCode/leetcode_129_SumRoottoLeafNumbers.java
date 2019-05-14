public class leetcode_129_SumRoottoLeafNumbers {
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int[] result = new int[] {0};
        traversal(root, "", result);
        
        return result[0];
    }
    
    public void traversal (TreeNode node, String fromStr, int[] result) {
        fromStr = fromStr + node.val;
        if (node.left == null && node.right == null) {
            result[0] += Integer.parseInt(fromStr);
        }
        
        if (node.left != null) {
            traversal(node.left, fromStr, result);
        }
        
        if (node.right != null) {
            traversal(node.right, fromStr, result);
        }
    }
}
