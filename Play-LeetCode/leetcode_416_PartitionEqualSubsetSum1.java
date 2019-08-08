package com.fightzhong.I_leetcode;

/**
 * 思路: 要想分割成两份, 那么就是说所有的数加起来是一个偶数, 因为每一个数都是正整数,
 *       所以对于加起来是奇数的直接返回false即可, 然后我们计算所有数的总和sum1,
 *       然后在数组中查找是否存在数为sum1 / 2的, 如果存在则说明能够分割, 反之则不能
 *
 * 注意: 这里我们用Boolean数组, 是因为可以初始化为null, 当我们记忆化搜索的时候, 对于
 *       非null的值说明已经求得结果, 则不用再递归下去了
 */
public class leetcode_416_PartitionEqualSubsetSum1 {
	private Boolean[][] memo;
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

		memo = new Boolean[nums.length][sum + 1];

		return getSum( nums.length - 1, sum, nums );
	}

	// 判断数组nums中是否存在和为sum的值
	public boolean getSum (int index, int sum, int[] nums) {
		if ( index < 0 || sum < 0 ) {
			return false;
		}

		if ( memo[index][sum] != null ) {
			return memo[index][sum];
		}

		if ( sum == 0 ) {
			return true;
		}

		boolean res1 = getSum( index - 1, sum - nums[index], nums );
		boolean res2 = getSum( index - 1, sum, nums );
		boolean res = res1 || res2;
		memo[index][sum] = res;

		return res;
	}
}
