import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 */
public class leetcode_257_BinaryTreePaths2 {
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> list = new ArrayList<String>();
                
        if (root == null) {
            return list;
        }
        
        toLeaves(root, "", list);
        
        return list;
    }
    
    public void toLeaves (TreeNode node, String from, ArrayList<String> list) {
        // 叶子节点
        if (node.left == null && node.right == null) {
            list.add(from + node.val);
            return;
        }
        
        if (node.left != null) {
            toLeaves(node.left, from + node.val + "->", list);
        }
        
        if (node.right != null) {
            toLeaves(node.right, from + node.val + "->", list);
        }
    }
}
