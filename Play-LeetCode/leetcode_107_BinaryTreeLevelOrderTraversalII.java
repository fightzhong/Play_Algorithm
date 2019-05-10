import java.util.ArrayList;
import java.util.List;

public class leetcode_107_BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<List<Integer>> list  = new ArrayList<List<Integer>>();
        
        traversal(root, list, 1);
        
        // 将数据进行反转
        ArrayList<List<Integer>> resultList = new ArrayList<List<Integer>>();
        for (int i = list.size() - 1; i >= 0; i--) {
            resultList.add(list.get(i));
        }
        
        return resultList;
    }
    
    public void traversal (TreeNode node, ArrayList<List<Integer>> list, int level) {
        if (node == null) {
            return;
        }

        if (level == list.size() + 1) {
            list.add(new ArrayList<Integer>());
        }
        
        list.get(level - 1).add(node.val);
        
        traversal(node.left, list, level + 1);
        traversal(node.right, list, level + 1);
    }
}










