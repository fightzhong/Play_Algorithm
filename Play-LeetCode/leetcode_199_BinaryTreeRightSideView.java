import java.util.ArrayList;
import java.util.List;

public class leetcode_199_BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        List<List<Integer>> levelList = new ArrayList<>();
        
        traversal(root, levelList, 0);
        
        for (List<Integer> l: levelList) {
            list.add(l.get(l.size() - 1));
        }
        
        return list;
    }
    
    public void traversal (TreeNode node, List<List<Integer>> levelList, int level) {
        if (node == null) {
            return;
        }
        
        if (levelList.size() == level) {
            levelList.add(new ArrayList<Integer>());
        }
        
        levelList.get(level).add(node.val);
        traversal(node.left, levelList, level + 1);
        traversal(node.right, levelList, level + 1);
    }
}
