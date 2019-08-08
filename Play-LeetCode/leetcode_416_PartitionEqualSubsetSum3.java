package com.fightzhong.I_leetcode;

/**
 * 优化: 同背包问题一样, 我们发现每计算一行的值只需要上一行的值, 并且每一列的值只需要上一行前面的值
 * 所以我们可以将空间压缩到一行来解决
 */
public class leetcode_416_PartitionEqualSubsetSum3 {
	public boolean canPartition(int[] nums) {
		// 获取总的和, 求一半的值, 那么等于在这个数组中求和为该值是否存在
		int sum = 0;
		for ( int i: nums ) {
			sum += i;
		}

		if ( sum % 2 != 0 ) {
			return false;
		}

		sum /= 2;

		boolean[] memo = new boolean[sum + 1];
		// 初始化第一行, 只有在i == nums[0], 即sum刚好等于该值的时候才能够是true
		for ( int i = 0; i < sum + 1; i ++ ) {
			memo[i] = i == nums[0] ? true : false;
		}

		// 遍历nums数组中所有的数
		for ( int i = 1; i < nums.length; i ++ ) {
			// 对每一行中的所有列进行遍历
			for ( int j = sum; j >= 0; j -- ) {
				// 如果j比nums[i]小, 那么前i个元素能否找到和为j的值就应该看之前的值, 所以值就不用更新
				if ( j < nums[i] ) {
					break;
				}

				// 选择当前的值
				boolean res1 = memo[j - nums[i]];
				// 不选择当前的值
				boolean res2 = memo[j];

				memo[j] = res1 || res2;
			}
		}

		return memo[sum];
	}

}
