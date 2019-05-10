import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**由于每一层的元素都要放在同一个数组中, 我们需要知道每一个元素的层数, 这里通过额外定义一个Element对象来实现**/
public class leetcode_102_BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> list = new ArrayList<List<Integer>>();
        if (root == null) {
            return list;
        }
        
        LinkedList<Element> queue = new LinkedList<>();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(root.val);
        list.add(arr);
        queue.addLast(new Element(root, 1));
        
        while (!queue.isEmpty()) {
            Element ele = queue.removeFirst();
            TreeNode node = ele.node;
            
            if (ele.level == list.size()) {
                list.add(new ArrayList<Integer>());
            }
            
            if (node.left != null) {
                queue.addLast(new Element(node.left, ele.level + 1));
                list.get(ele.level).add(node.left.val);
            }
            
            if (node.right != null) {
                queue.addLast(new Element(node.right, ele.level + 1));
                list.get(ele.level).add(node.right.val);
            }
        }
        
        if (list.get(list.size() - 1).size() == 0) {
            list.remove(list.size() - 1);
        }
        
        return list;
    }
    
    private class Element {
        int level;
        TreeNode node;
        
        public Element (TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
}
