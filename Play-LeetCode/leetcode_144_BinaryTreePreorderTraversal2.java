import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class leetcode_144_BinaryTreePreorderTraversal2 {
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            
            if (node.right != null) {
                stack.push(node.right);
            }
            
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        
        return list;
    }
}
