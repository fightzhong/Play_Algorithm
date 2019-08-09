/**
 * 在上一版本的解法中使用记忆化搜索进行优化, 对[i, target]进行记忆
 */
public class leetcode_377_CombinationSumIV2 {
	private Integer[][] memo;  // 这里用Integer的原因是因为用null来记忆没有被记忆的组合
	public int combinationSum4(int[] nums, int target) {
		memo = new Integer[nums.length][target + 1];
		return getCombinationSum( nums, target );
	}

	private int getCombinationSum (int[] nums, int target) {
		if ( target < 0 ) {
			return 0;
		}

		if ( target == 0 ) {
			return 1;
		}

		int count = 0;
		for ( int i = 0; i < nums.length; i ++ ) {
			// 如果已经被记忆了, 那么就直接拿到记忆的个数进行相加
			if ( memo[i][target] != null ) {
				count += memo[i][target];
			} else {
				// 对值进行记忆
				memo[i][target] = getCombinationSum(nums, target - nums[i]);
				count += memo[i][target];
			}
		}

		return count;
	}
}
