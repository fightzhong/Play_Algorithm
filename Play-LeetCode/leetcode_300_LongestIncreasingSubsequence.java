

public class leetcode_300_LongestIncreasingSubsequence {
	public int lengthOfLIS(int[] nums) {
		return getLengthOfLIS( nums, 0 );
	}

	// 获取从[index, nums.length - 1]的最长上升子序列的长度
	public int getLengthOfLIS (int[] nums, int index) {
		if ( index >= nums.length ) {
			return 0;
		}

		// 选择当前的值
		// 获取下一个比当前值大的索引
		int nextIndex = index + 1;
		while ( nextIndex < nums.length && nums[nextIndex] <= nums[index] ) {
			nextIndex++;
		}
		int count1 = 1 + getLengthOfLIS( nums, nextIndex );

		// 不选择当前的值
		int count2 = getLengthOfLIS( nums, index + 1 );

		return Math.max( count1, count2 );
	}
}
