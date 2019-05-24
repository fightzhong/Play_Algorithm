import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *  
 *  类似于39题, 不同的是39题不会出现重复的元素, 并且一个元素可以用多次
 *  而此题会出现重复元素, 并且一个元素只能用一次, 我们在已经找到一个组合的情况下, 应该防止再找到相同的组合
 *  解决方案: 
 *          对元素进行从小到大排序, 那么在一次循环中, ,每次只对所有相同的元素中的第一个元素进行查找组合, 查找结束后
 *          转向下一个不同的元素
 */
public class leetcode_040_CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        return getCombination( 0, candidates.length - 1, candidates, target );
    }
    
    public List<List<Integer>> getCombination (int l, int r, int[] candidates, int target){
        ArrayList<List<Integer>> list = new ArrayList<List<Integer>>();

        // 找不到组合的情况: 已经不存在元素了, 或者target已经小于0了
        // 这里之所以不是l > r, 而是 l - 1 > r, 是因为我们判断的是target = 0的情况
        if ( l - 1 > r ||  target < 0 ) {
            return null;
        }
        
        // 找到组合的情况
        if ( target == 0 ) {
            ArrayList<Integer> alist = new ArrayList<Integer>();
            list.add( alist );
            return list;
        }
        
        
        for ( int i = l; i <= r; i ++ ) {
            int nextTarget = target - candidates[i];
            List<List<Integer>> returnList = getCombination( i + 1, r, candidates, nextTarget );
            
            if ( returnList != null ) {
                for ( List<Integer> alist: returnList ) {
                    alist.add( candidates[i] );
                    list.add( alist );
                }
            }
            
            // i应该指向下一个不等于当前值的位置
            // 先找到最后一个与i相等的索引
            while ( i + 1 <= r && candidates[i + 1] == candidates[i] ) {
                i ++;
            }
        }
        
        return list;
    }
}
