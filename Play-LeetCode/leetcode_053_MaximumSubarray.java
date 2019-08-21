/**
 * 方法一: 通过双层循环, 暴力获得所有的子区间的和, 在获取的过程中不停的更新最大值
 *
 * 方法二: 动态规划解法, memo[i] 表示[0, i]中最大的子序列和,
 *         状态转移方程: memo[i] = Max( memo[i - 1] + nums[i], nums[i] )
 *          如果前面的即memo[i - 1]的最大子序列和加上nums[i]之后的最大子序列和大于当前的值nums[i],
 *          memo[i]的最大子序列和即为memo[i - 1] + nums[i], 否则为nums[i]
 */
public class leetcode_053_MaximumSubarray {
	public int maxSubArray(int[] nums) {
		if ( nums.length == 1 ) return nums[0];

		int[] memo = new int[nums.length];
		memo[0] = nums[0];

		int max = memo[0];
		for ( int i = 1; i < memo.length; i ++ ) {
			memo[i] = Math.max( nums[i] + memo[i - 1], nums[i] );
			max = Math.max( max, memo[i] );
			System.out.print( memo[i] + "   " );
		}

		return max;
	}
}
