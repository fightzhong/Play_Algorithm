import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 优化方案: 
 *      对于第一种解决方案, 这里进行优化, 首先对数组进行排序, 然后在查找组合的时候, 为了防止下一次相同层次下再考虑这个值
 *   for循环结束一层后, 立马将i指向下一个不等于当前i指向的值
 */
public class leetcode_090_SubsetsII2 {
    public static void main(String[] args) {
        int[] nums = new int[] {4,4,4,1,4};
        System.out.println(new leetcode_090_SubsetsII2().subsetsWithDup(nums));
    }
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        return getSubsets( -1, nums );
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
            
            // 找到下一个不等于当前i指向的值的索引
            while ( i + 1 <= nums.length - 1 && nums[ i + 1 ] == nums[ i ] ) {
                i ++;
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
