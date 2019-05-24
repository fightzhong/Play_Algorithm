import java.util.ArrayList;
import java.util.List;

public class leetcode_216_CombinationSumIII {
    public static void main(String[] args) {
        int k = 2;
        int n = 9;
        
        System.out.println(new leetcode_216_CombinationSumIII().combinationSum3(k, n));
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        ArrayList<List<Integer>> allList = getCombination( 0, 0, k, n );
        return allList == null ? new ArrayList<List<Integer>>() : allList;
    }
    
    /**
     * cur: 当前值
     * count: 正在考虑第几个数
     * k: 需要考虑的个数
     * target: 目标值
     */
    public ArrayList<List<Integer>> getCombination (int cur, int count, int k, int target) {
        ArrayList<List<Integer>> allList = new ArrayList<List<Integer>>();
        
        // 找到了个数
        if ( count == k ) {
            // 判断target是否和当前值相等
            if ( cur == target ) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add( cur );
                allList.add( list );
                return allList;
            }
            return null;
        }
        
        int nextTarget = target - cur;
        for ( int i = cur + 1; i <= 9; i ++ ) {
            // k - count: 还需要多少个数, 如果这个值小于9 - cur , 就不用再考虑了, 因为一直考虑到底都不能找到指定的个数了
            // 如果nextTarget比i小, 那么就一定找不到nextTarget了
            if ( k - count > 9 - cur || nextTarget < i ) {
                break;
            }
            
            ArrayList<List<Integer>> nextAllList = getCombination( i, count + 1, k, nextTarget );
            if ( nextAllList != null ) {
                for ( List<Integer> l: nextAllList ) {
                    // 因为我们是从0开始算的,所以我们在count == 0的时候就不能将cur = 0加进去了
                    if ( count != 0 ) {
                        l.add( cur );
                    }
                        allList.add( l );
                }
            }
        }
        
        return allList.size() == 0 ? null : allList;
    }
}
