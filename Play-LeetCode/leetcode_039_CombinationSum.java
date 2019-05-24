import java.util.ArrayList;
import java.util.List;

/**
 *  思路: 
 *       <1> 要找到组合相加等于target的情况
 *       <2> 以 2, 3, 5, target为8为例
 *       <3> 第一个值为2, 则还需要target - 2即6
 *       <4> 那么此时应该去2, 3, 5执行第三步, 则第二个值为2, 那么就还需要 target(6) - 2 即4
 *       <5> 以此类推, 当找到target = 0时, 说明找到一个组合, 此时返回一个List<List<Integer>>, 里面放了List<Integer>
 *       <6> 到了上一层, 则将上一层的元素加入List<Integer>, 然后创建自己的List<List<Integer>>, 将整合的List<Integer>放入自己的List
 */
public class leetcode_039_CombinationSum {
    
    public static void main(String[] args) {
        int[] candidates = new int[] {2,3,5};
        int target = 8;
        System.out.println( new leetcode_039_CombinationSum().combinationSum(candidates, target) );
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<List<Integer>> allList = getCombinationSum( 0, candidates.length - 1, candidates, target);
        return allList;
    }
    
    // 获取数组中[l, r]区间内和为target的所有组合
    public ArrayList<List<Integer>> getCombinationSum (int l, int r, int[] candidates, int target) {
        ArrayList<List<Integer>> list = new ArrayList<List<Integer>>();
        // 没找到组合情况, 则返回null
        if ( target < 0 ) {
            return null;
        }
        
        // 找到组合情况, 则返回一个数组, 用于向上回溯时添加值
        if ( target == 0 ) {
            ArrayList<Integer> alist = new ArrayList<Integer>();
            list.add( alist );
            return list;
        }
        
        
        for ( int i = l; i < candidates.length; i ++ ) {
            int nextTarget = target - candidates[i];
            ArrayList<List<Integer>> returnList = getCombinationSum( i, r, candidates, nextTarget );
            
            if ( returnList == null ) {
                continue;
            } else {
                for ( List<Integer> alist: returnList ) {
                    alist.add( candidates[i] );
                    list.add( alist );
                }
            }
        }
        
        return list;
    }
    
}
