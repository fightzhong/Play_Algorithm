package com.fightzhong.I_leetcode;

/**
 * 自底向上的动态规划:
 *     我们开辟一个二维的数组, 每一行代表nums中的每一个数字, 每一列代表0-sum/2之间的每一个数字
 *     利用双层for循环, 求在前x个元素中能否找到和为对应的值y, 比如memo[3][5], 即求在nums数组
 *     中索引为0-3的数是否存在和为5的值
 */
public class leetcode_416_PartitionEqualSubsetSum2 {
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

		boolean[][] memo = new boolean[nums.length][sum + 1];
		// 初始化第一行, 只有在i == nums[0], 即sum刚好等于该值的时候才能够是true
		for ( int i = 0; i < sum + 1; i ++ ) {
			memo[0][i] = i == nums[0] ? true : false;
		}

		// 遍历所有的行
		for ( int i = 1; i < memo.length; i ++ ) {
			// 对每一行中的所有列进行遍历
			for ( int j = 0; j < sum + 1; j ++ ) {
				// 当j < nums[i]时, 判断是否在前i个元素中存在和为j的值, 由于当前nums[i]是比j大的
				// 所以当前元素nums[i]是不能计入其中的, 那么这里的值就应该等于上一层的值即memo[i - 1][j]
				if ( j < nums[i] ) {
					memo[i][j] = memo[i - 1][j];
				} else {
					// 选择当前的值
					boolean res1 = memo[i - 1][j - nums[i]];
					// 不选择当前的值
					boolean res2 = memo[i - 1][j];
					memo[i][j] = res1 || res2;
				}
			}
		}

		return memo[memo.length - 1][sum];
	}

}
