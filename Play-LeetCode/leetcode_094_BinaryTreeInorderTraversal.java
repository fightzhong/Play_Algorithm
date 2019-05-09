import java.util.ArrayList;
import java.util.List;

public class leetcode_094_BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        inOrder(root, list);
        return list;
    }
    
    public void inOrder (TreeNode curNode, List<Integer> list) {
        if (curNode == null) {
            return;
        }

        inOrder(curNode.left, list);
        list.add(curNode.val);
        inOrder(curNode.right, list);
    }
}
