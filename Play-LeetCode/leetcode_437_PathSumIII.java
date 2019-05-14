import java.util.ArrayList;
/**
      思路:
         由于我们需要求得是连续的从父节点到字节点的值相加等于sum的, 那么我们就没遍历一个子节点
         都从该子节点往前遍历到根节点, 并不停的相加值, 如果相加后的值等于sum, 那么就说明多了一个路径
     
     方法:
     traversal: 该方法就是遍历二叉树的
     getSumCount: 获取从子节点到父节点的值相加等于sum的个数
     
    ArrayList<Integer> fromEle: 记录父节点到当前节点的路径 
 */
public class leetcode_437_PathSumIII {
    public int pathSum(TreeNode root, int sum) {
        int[] count = new int[] {0};
        ArrayList<Integer> fromEle = new ArrayList<Integer>();
        
        traversal(root, sum, fromEle, count);
        
        return count[0];
    } 
    
    public void traversal (TreeNode node, int sum, ArrayList<Integer> fromEle, int[] count) {
        if (node == null) {
            return;
        }
        
        fromEle.add(node.val);
        count[0] += getSumCount(fromEle, sum);
        
        // 复制一份fromEle, 传给右边的节点, 否则两个节点就共用一个对象了!!
        ArrayList<Integer> fromEle2 = new ArrayList<Integer>();
        for (Integer i: fromEle) {
            fromEle2.add(i);
        }
        
        traversal(node.left, sum, fromEle, count);
        traversal(node.right, sum, fromEle2, count);
    }
    
    /**对数组中进行查找, 与结尾相连不间断的所有相加的值为target的是否存在**/
    public int getSumCount (ArrayList<Integer> fromEle, int target) {
        int sum = 0;
        int count = 0;
        for (int i = fromEle.size() - 1; i >= 0; i --) {
            sum += fromEle.get(i);
            if (sum == target) {
                count ++;
            }
        }
        
        return count;
    }
}
