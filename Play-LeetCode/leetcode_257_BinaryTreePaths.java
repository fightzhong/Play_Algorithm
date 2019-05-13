import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
      数据结构:
          <1> HashMap<TreeNode, TreeNode> from: 每遍历一个节点, 就在from里面记录该节点是从哪里来的
          <2> ArrayList<Integer> arr: 当遍历到叶子节点的时候, 创建该arr, 然后通过from来回溯该叶子节点的来源, 并将该来源添加进arr中
          <3> ArrayList<ArrayList<Integer>> list: 上一步结束后, 说明得到了一条从根节点到叶子节点的路, 此时将这个路加入到list中
          <4> ArrayList<String> result: 对list进行遍历, 将里面的每一条路变成字符串, 并将字符串放入result中
 */
public class leetcode_257_BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<String> result = new ArrayList<String>();
        HashMap<TreeNode, TreeNode> from = new HashMap<TreeNode, TreeNode>();
        
        if (root == null) {
            return result;
        }
        
        from.put(root, null);
        
        toLeaves(root, from, list);
        
        for (ArrayList<Integer> l: list) {
            StringBuilder str = new StringBuilder();
            for (int i = l.size() - 1; i >= 0; i--) {
                str.append(l.get(i));
                if (i != 0) {
                    str.append("->");
                }
            }
            
            result.add(str.toString());
        }
        
        
        return result;
    }
    
    public void toLeaves (TreeNode node, HashMap<TreeNode, TreeNode> from, ArrayList<ArrayList<Integer>> list) {
        // 叶子节点
        if (node.left == null && node.right == null) {
            ArrayList<Integer> path = new ArrayList<Integer>();
            while (node != null) {
                path.add(node.val);
                node = from.get(node);
            }
            
            list.add(path);
            return;
        }
         
        if (node.left != null) {
            from.put(node.left, node);
            toLeaves(node.left, from, list);
        }
        
        if (node.right != null) {
            from.put(node.right, node);
            toLeaves(node.right, from, list);
        }
    }
}
