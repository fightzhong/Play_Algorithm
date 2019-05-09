import java.util.ArrayList;
import java.util.List;

public class leetcode_144_BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }
    
    public void preOrder (TreeNode curNode, List<Integer> list) {
        if (curNode == null) {
            return;
        }
        
        list.add(curNode.val);
        preOrder(curNode.left, list);
        preOrder(curNode.right, list);
    }
}
