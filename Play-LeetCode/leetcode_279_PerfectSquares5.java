import java.util.HashSet;

/**
    动态规划的解法
 */
public class leetcode_279_PerfectSquares5 {
    public int numSquares(int n) {
        int[] counts = new int[n + 1];
        counts[0] = 0;
        
        for ( int i = 1; i <= n; i ++ ) {
            int min = Integer.MAX_VALUE;
            int square = 1;
            while ( i - square * square >= 0 ){
                min = Math.min( min, 1 + counts[i - square * square] );
                square ++;
            }
            
            counts[i] = min;
        }
        
        return counts[n]; 
    }
}
