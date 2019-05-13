import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
      HashMap<TreeNode, TreeNode> from: 用于存储当前节点是从哪里来的
      ArrayList<List<Integer>> list: 用于存储所有的路径
      
          找到路径后, 创建一个ArrayList<Integer>, 然后将路径放入这个数据结构中, 再将这个路径放入list
 */
public class leetcode_113_PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<List<Integer>> list = new ArrayList<List<Integer>>();
        
        if (root == null) {
            return list;
        }
        
        HashMap<TreeNode, TreeNode> from = new HashMap<>();
        from.put(root, null);
        
        traversal(root, 0, from, sum, list);
        
        return list;
    }
    
    public void traversal (TreeNode node, int fromSum, HashMap<TreeNode, TreeNode> from, int sum, ArrayList<List<Integer>> list) {
        // 叶子节点
        if ( node.left == null && node.right == null ) {
            // 符合条件的路径, 将这个路径放入list
            if (fromSum + node.val == sum) {
               ArrayList<Integer> l = new ArrayList<Integer>();
               while (node != null) {
                   l.add(node.val);
                   node = from.get(node);
               }
               
               ArrayList<Integer> l2 = new ArrayList<Integer>();
               for (int i = l.size() - 1; i >= 0; i --) {
                   l2.add(l.get(i));
               }
               
               list.add(l2);
               return;
            }
        }
        
        if (node.left != null) {
            from.put(node.left, node);
            traversal(node.left, fromSum + node.val, from, sum, list);
        }
        
        if (node.right != null) {
            from.put(node.right, node);
            traversal(node.right, fromSum + node.val, from, sum, list);
        }
    }
    
}
