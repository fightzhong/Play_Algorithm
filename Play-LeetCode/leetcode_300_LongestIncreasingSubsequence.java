/**
 * 从后往前遍历, 依次计算出每一个值的最长上升子序列
 * 对于第index个值来说, 其最长上升子序列的大小为从index开始到数组末尾中所有比该元素大的值
 * 中最长上升子序列最大的那个值加上一
 */
public class leetcode_300_LongestIncreasingSubsequence {
	public int lengthOfLIS(int[] nums) {
		if ( nums.length == 0 ) {
			return 0;
		}

		// 最长上升子序列的大小
		int count = 0;
		int[] memo = new int[nums.length];
		memo[nums.length - 1] = 1;

		for ( int i = nums.length - 1; i >= 0; i-- ) {
			// 依次获取第i个元素后面所有比该元素大的元素索引
			int nextBigIndex = i + 1;

			// 初始化为1, 即没有找到比该元素大的元素时, 则第i个元素的最长上升子序列的值为1
			memo[i]= 1;
			while ( nextBigIndex < nums.length ) {
				if ( nums[nextBigIndex] > nums[i] && memo[i] < 1 + memo[nextBigIndex] ) {
					memo[i] = 1 + memo[nextBigIndex];
				}
				nextBigIndex++;
			}

			// 更新最长上升子序列的大小
			count = count < memo[i] ? memo[i] : count;
		}

		return count;
	}
}
