import java.util.Arrays;

public class leetcode_198_HouseRobber2 {
    public static void main(String[] args) {
        System.out.println( new leetcode_198_HouseRobber2().rob( new int[] {1,2,3,1} ) );
    }
    
    private int[] robMoney;
    public int rob(int[] nums) {
        if ( nums.length == 0 ) {
            return 0;
        }

        int[] memo = new int[nums.length];  // 记忆偷取前n个房子能获得的最大值
        memo[0] = nums[0];

        for ( int i = 1; i < nums.length; i ++ ) {
            // 考虑偷取前i个房子能获得的最大收益(有两种情况, 偷当前的房子, 不偷当前的房子)

            // 偷取前i-2个房子加上当前的房子
            int m1 = i - 2 >= 0 ? memo[i - 2] + nums[i] : nums[i];
            // 不偷当前的房子, 偷取前i-1个房子
            int m2 = memo[i - 1];
            memo[i] = Math.max( m1, m2 );
        }

        return memo[memo.length - 1];
    }
}
