import java.util.ArrayList;
import java.util.List;
/**
 * - 思路: [ 4, 5, 6 ]
 *            4     5   6
 *           / \    /
 *          5   6   6
 *         /
 *        6
 *        
 *    注意: 4, 5这种情况, 我们可以通过在考虑5的时候, 往allList中添加一个ArrayList<Integer> list, 这个list中再加入5, 这样上一层
 *                在整合的时候就能得到4,5的结果了
 */
public class leetcode_078_Subsets {
    public static void main(String[] args) {
        int[] nums = new int[] {4,5,6};
        System.out.println(new leetcode_078_Subsets().subsets(nums));
    }
    
    public List<List<Integer>> subsets(int[] nums) {
        ArrayList<List<Integer>> allList = getSubsets( -1, nums );
        return allList;
    }
    
    public ArrayList<List<Integer>> getSubsets (int index, int[] nums) {
        ArrayList<List<Integer>> allList = new ArrayList<List<Integer>>();
        
        // 递归到最后一个元素
        if ( index == nums.length - 1 && index != -1 ) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add( nums[index] );
            allList.add( list );
            return allList;
        }
        
        for ( int i = index + 1; i <= nums.length - 1; i ++ ) {
            ArrayList<List<Integer>> returnList = getSubsets( i , nums );
            for ( List<Integer> l: returnList ) {
                if ( index != -1 ) {
                    l.add( nums[index] );
                }
                allList.add( l );
            }
            
        }
        
        if ( index != -1 ) {
            ArrayList<Integer> l = new ArrayList<Integer>();
            l.add( nums[index] );
            allList.add( l );
        } else {
            allList.add( new ArrayList<Integer>() );
        }
        
        return allList;
    }
}
