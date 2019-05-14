import java.util.ArrayList;
/**
      思路:
         
 */
public class leetcode_437_PathSumIII2 {
    public int pathSum(TreeNode root, int sum) {
        int[] count = new int[] {0};
        traversal(root, sum, count);
        return count[0];
    } 
    
    public ArrayList<Integer> traversal (TreeNode node, int sum, int[] count) {
        ArrayList<Integer> sums = new ArrayList<Integer>();
        if (node == null) {
            return sums;
        }
        
        ArrayList<Integer> leftSum = traversal(node.left, sum, count);
        ArrayList<Integer> rightSum = traversal(node.right, sum, count);
        ArrayList<Integer> curSum = new ArrayList<Integer>();
        
        curSum.add(node.val);
        if (node.val == sum) {
            count[0] ++;
        }
        
        for (Integer i: leftSum) {
            curSum.add(i + node.val);
            if (i + node.val == sum) {
                count[0] ++;
            } 
        }
        
        for (Integer i: rightSum) {
            curSum.add(i + node.val);
            if (i + node.val == sum) {
                count[0] ++;
            } 
        }
        
        return curSum;
    }
}
