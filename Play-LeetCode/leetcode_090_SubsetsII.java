import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 类似于78题, 这里的解决方式是: 将数组先进行排序, 然后找到所有的组合, 最后通过set数据结构去重
 */
public class leetcode_090_SubsetsII {
    public static void main(String[] args) {
        int[] nums = new int[] {4,4,4,1,4};
        System.out.println(new leetcode_090_SubsetsII().subsetsWithDup(nums));
    }
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        ArrayList<List<Integer>> allList = getSubsets( -1, nums );
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        for ( List<Integer> l: allList ) {
            set.add( l );
        }
        
        allList = new ArrayList<List<Integer>>();
        for ( List<Integer> l: set ) {
            allList.add( l );
        }
        
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
