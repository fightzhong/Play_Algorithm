import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class leetcode_145_BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
       ArrayList<Integer> list = new ArrayList<>();
       postOrder(root, list);
       return list;
    }
    
    public void postOrder (TreeNode node, ArrayList<Integer> list) {
        if (node == null) {
            return;
        }
        
        postOrder(node.left, list);
        postOrder(node.right, list);
        list.add(node.val);
    }
}
