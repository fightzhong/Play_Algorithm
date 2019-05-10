import java.util.ArrayList;
import java.util.List;

public class leetcode_103_BinaryTreeZigzagLevelOrderTraversa {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<List<Integer>> list = new ArrayList<List<Integer>>();
        
        traversal(root, list, 0);
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 != 0) {
                reverse(list.get(i));
            }
        }
        
        return list;
    }
    
    public void traversal (TreeNode node, ArrayList<List<Integer>> list, int level) {
        if (node == null) {
            return;
        }
        
        if (level == list.size()) {
            list.add(new ArrayList<Integer>());
        }
        
        list.get(level).add(node.val);
            
        traversal(node.left, list, level + 1);
        traversal(node.right, list, level + 1);
    }
    
    /**对数组进行反转**/
    public void reverse (List<Integer> list) {
        int l = 0;
        int r = list.size() - 1;
        
        while (l < r) {
            int temp = list.get(l);
            list.set(l, list.get(r));
            list.set(r, temp);
            l++;
            r++;
        }
    }
}










