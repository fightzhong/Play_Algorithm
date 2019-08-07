/**
 * 动态规划: 等价于198题, 不同的是这里需要偷两次
 * 状态转移方程: f(n) 代表偷取前n个房子, val(n)代表第n个房子的价值(里面的钱)
 *  f(n) = max( f(n - 2) + n, f(n - 1) )
 */
public class leetcode_213_HouseRobberII2 {
    public int rob(int[] nums) {
        if ( nums.length == 0 ) {
            return 0;
        }

        // 方式一: 偷取第一个房子, 那么下面的遍历就不能遍历到最后一个房子, 而应该遍历到倒数第二个
        int[] memo = new int[nums.length];
        memo[0] = nums[0];

        for ( int i = 1; i < nums.length - 1; i ++ ) {
            int m1 = i - 2 >= 0 ? memo[i - 2] + nums[i] : nums[i];
            int m2 = memo[i - 1];
            memo[i] = Math.max( m1, m2 );
        }

        // 方式二: 不偷取第一个房子, 那么下面的遍历就能遍历到最后一个房子
        int[] memo2 = new int[nums.length];
        memo2[0] = 0;

        for ( int i = 1; i < nums.length; i ++ ) {
            int m1 = i - 2 >= 0 ? memo2[i - 2] + nums[i] : nums[i];
            int m2 = memo2[i - 1];
            memo2[i] = Math.max( m1, m2 );
        }

        // 则偷取房子的最大值应该等于方式一中memo[nums.length-2]和方式二中memo[nums.length-1]的最大值
        int max = nums.length - 2 >= 0 ? Math.max( memo[nums.length-2], memo2[nums.length-1] ) : memo[nums.length-1];
        return max;
    }
}
