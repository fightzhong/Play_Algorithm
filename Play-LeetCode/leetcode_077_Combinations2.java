import java.util.ArrayList;
import java.util.List;
/**
      优化: 
          简之: 
                  组合个数是3个
          r - l 即被考虑的元素的若为4 - 3
          index为当前正在被考虑的第index个组合数
          
          如果组合个数 - index >  r - l, 说明剩下组合个数的要求个数大于了可以考虑的个数, 此时应该直接不再去考虑了
          优化代码: 第48行
 
 */
public class leetcode_077_Combinations2 {
    public static void main(String[] args) {
        System.out.println(new leetcode_077_Combinations2().combine(4, 3));
    }
    
    public List<List<Integer>> combine(int n, int k) {
        // 获取 1 => n, 组合个数为k的组合情况
        return getCombination( 1, n, 1, k );
    }
    
    // 获取[l, r]闭区间的组合情况
    private List<List<Integer>> getCombination (int l, int r, int index, int k) {
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
            if ( r - l + index < k ) {
                break;
            }
            
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
