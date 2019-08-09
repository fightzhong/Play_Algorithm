import java.util.HashMap;

public class leetcode_494_TargetSum {
	public int findTargetSumWays(int[] nums, int S) {
		return findTargetSumWays2( nums, S, nums.length - 1 );
	}

	public int findTargetSumWays2 (int[] nums, int target, int index) {
		if ( index == 0 ) {
			int count = 0;
			count += target == nums[index] ? 1 : 0;
			count += target == -nums[index] ? 1 : 0;
			return count;
		}

		// 对索引为index的数来说, 可以选择为正数和负数
		int count1 = findTargetSumWays2( nums, target - nums[index], index - 1 );
		int count2 = findTargetSumWays2( nums, target + nums[index], index - 1 );

		return count1 + count2;
	}
}
