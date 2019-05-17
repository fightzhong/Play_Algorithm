import java.util.ArrayList;
import java.util.List;

public class leetcode_077_Combinations {
    public static void main(String[] args) {
        System.out.println(new leetcode_077_Combinations().combine(4, 3));
    }
    
    public List<List<Integer>> combine(int n, int k) {
        // 获取 1 => n, 组合个数为k的组合情况
        return getCombination( 1, n, 1, k );
    }
    
    // 获取[l, r]闭区间的组合情况
    private List<List<Integer>> getCombination (int l, int r, int index, int k) {
        // l >= r 说明最多只有一个元素可以用来组合, index <= k, 说明即使加上l元素, 也不能达到k个
        if ( l >= r && index < k ) {
            return null;
        }
        
        ArrayList<List<Integer>> allList = new ArrayList<List<Integer>>();
        // 达到了要求
        if ( index == k ) {
            // l 到 r 可能有多种取法
            while ( l <= r ) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add( l );
                allList.add( list );
                l ++;
            }
            
            return allList;
        }
        
        
        // 固定i, 获取 [ i + 1, r ]的组合情况
        for ( int i = l; i <= r; i ++ ) {
            List<List<Integer>> sigetCombination = getCombination( i + 1, r, index + 1, k );
            if ( sigetCombination != null ) {
                // 将获取到的 [i + 1] 组合情况整合当前元素
                for ( List<Integer> list: sigetCombination ) {
                    list.add( i );
                    allList.add( list );
                }
            }
        }
        return allList;
    }
}
