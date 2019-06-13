/**
 * 动态规划: 等价于198题, 不同的是这里需要偷两次, 一次偷区间为[0, nums.length - 1]的所有房子, 一次偷区间为[1, nums.length - 2]的所有房子
 * 即偷第一个不偷最后一个 和 偷最后一个不偷第一个
 */
public class leetcode_213_HouseRobberII2 {
    public int rob(int[] nums) {
        int len = nums.length;
        if ( len <= 0 ) {
            return 0;
        }
        
        if ( len == 1 ) {
            return nums[0];
        }
        
        if ( len == 2 ) {
            return Math.max( nums[0], nums[1] );
        }
        // 情况一: 偷区间范围为[0, len - 2]的房子, 即不偷最后一个房子
        int m1 = robMoney( 0, len - 2, nums );
        System.out.println(m1);
        
        // 情况二: 偷区间范围为[1, len - 1], 即不偷第一个房子
        int m2 = robMoney( 1, len - 1, nums );
        
        return Math.max( m1, m2 );
    }
    
    public int robMoney (int l, int r, int[] nums) {
        int count = r - l + 1; // 区间[l,r]中元素的个数
        if ( count == 2 ) {
            return Math.max( nums[l], nums[l + 1] );
        }
        
        int[] memo = new int[nums.length];
        memo[l] = nums[l];
        memo[l + 1] = nums[l + 1];
        
        for ( int i = l + 2; i <= r; i ++ ) {
            // 偷前i个房子, 并且偷第i个房子
            for ( int j = l; j < i - 1; j ++ ) {
                memo[i] = Math.max( memo[j] + nums[i], memo[i] );
            }
            
            // 偷前i个房子, 并且不偷第i个房子
            memo[i] = Math.max( memo[i], memo[i - 1] );
        }
        
        return memo[r];                
    }
}
