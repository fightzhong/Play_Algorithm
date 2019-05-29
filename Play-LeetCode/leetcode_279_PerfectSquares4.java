import java.util.HashSet;

/**
    动态规划的解法: 将一个数可以被划分为两个更小的数, 这个划分可以通过for循环划分多次, 划分后
    求这两个更小的数用最小个数的平方个数表示的个数, 维护一个count, 循环划分多次后count的值应该是这个循环过程中产生的count的最小值     
 */
public class leetcode_279_PerfectSquares4 {
    public static void main(String[] args) {
        System.out.println( new leetcode_279_PerfectSquares4().numSquares(13) );
    }
    public int numSquares(int n) {
        HashSet<Integer> list = new HashSet<>();
        int cur = 1; 
        int curSquare = 1;
        while ( curSquare <= n ) {
            list.add( curSquare );
            cur ++;
            curSquare = cur * cur;
        }
        
        int[] counts = new int[n + 1];
        counts[0] = 0;
        counts[1] = 1;
        for ( int i = 2; i <= n; i ++ ) {
            if ( list.contains(i) ) {
                counts[i] = 1;
            } else {
                int count = Integer.MAX_VALUE;
                for ( int j = 1; j <= i / 2; j ++ ) {
                    int c1 = counts[j];
                    int c2 = counts[i - j];
                    if ( c1 + c2 < count ) {
                        count = c1 + c2;
                    }
                }
                counts[i] = count;
            }
        }
        
        return counts[n];
    }
}
