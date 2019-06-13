/**
 * 动态规划:
 *      第一步: 找状态: n代表第索引为n的那一家, 函数f(n)表示求得偷前n家的最大值
 *      第二部: 设计状态转移方程: 偷前n家的最大值   等于   偷当前家的前 + 偷前n-1,n-2,n-3等家的钱的最大值, v(n)表示第n家有多少钱
 *          第n家有两种情况, 偷与不偷, 如果偷则 f(n) = v(n) + Max( f(n-1), f(n-2), f(n-3) ..... f(0) )
 *          如果不偷, 则偷前n家的钱的最大值等于偷前n-1家的钱的最大值f(n) = f(n - 1)
 *          最后取这两个值得最大值
 */
public class leetcode_198_HouseRobber3 {
    public int rob(int[] nums) {
        if ( nums.length == 0 ) {
            return 0;
        }
        
        if ( nums.length == 1 ) {
            return nums[0];
        }
        
        if ( nums.length == 2 ) {
            return Math.max( nums[0], nums[1] );
        }
        
        int[] memo = new int[nums.length];
        memo[0] = nums[0];
        memo[1] = nums[1];
        
        for ( int i = 2; i < memo.length; i ++ ) {
            for ( int j = 0; j <= i - 2; j ++ ) {
                memo[i] = Math.max( nums[i] + memo[j], memo[i] );
            }
            memo[i] = Math.max( memo[i], memo[i - 1] );
        }
        
        return memo[memo.length - 1];        
    }
}
