import java.util.HashMap;

/**
 * 第一个版本是利用记忆化搜索, 假装我们已经求得了后面的值, 即自顶向下的求
 * 这个版本的解法利用动态规划, 自底向上的求值, 先求3, 4, 5....一直求到n
 */
public class leetcode_070_ClimbingStairs2 {
    HashMap<Integer, Integer> visited = new HashMap<>();
    public int climbStairs(int n) {
        visited.put( 1, 1 );
        visited.put( 2, 2 );
        for ( int i = 3; i <= n; i ++ ) {
            visited.put( i, visited.get(i - 1) + visited.get( i - 2) );
        }
        
        return visited.get( n );
    }
}
