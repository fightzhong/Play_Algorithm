/**
 * 动态规划:
 *      一个数被拆分为两个以上的数, 求得这些数乘积最大的, 那么我们先把一个数拆分为两个数
 *      然后再对这个两个数进行拆分, 即求这两个数拆分后乘积的最大值
 */
public class leetcode_343_IntegerBreak {
    public int integerBreak(int n) {
        int[] maximize = new int[n + 1];
        maximize[1] = 1;
        maximize[2] = 1;
        
        for ( int i = 0; i <= n; i++ ) {
            int max = 0;
            // 对i的值进行拆分为两个数, 从1开始拆, 拆到i / 2
            for ( int j = 1; j <= i / 2; j ++ ) {
                // 此时被拆为了j , i - j
                // 首先判断maximize中j 和 i - j对应的最大值是否大于j 和 i - j, 如果大于则采用, 不大于则采用j 和 i - j本身
                int n1 = maximize[j] > j ? maximize[j] : j;
                int n2 = maximize[i - j] > ( i - j ) ? maximize[i - j] : ( i - j );
                if ( n1 * n2 > max ) {
                    max = n1 * n2;
                }
            }
            
            // 获得了最大的两数相加的值
            maximize[i] = max;
        }
        
        return maximize[n];
    }
}
