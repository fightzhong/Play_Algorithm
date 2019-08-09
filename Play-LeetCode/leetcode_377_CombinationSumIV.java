/**
 * 解题思路:
 *       根据题目要求, 所有的数都能重复使用, 那么为了组合成target, 第一个数可以是数组中的任意
 *       一个数, 第二个数也可以是数组中的任意一个数, 所以对于每一个数都可以是数组中的任意一个数,
 *       我们需要不停的进行循环, getCombinationSum(int[] nums, int target)方法的含义是求得
 *       在nums数组中, 和为target的组合有多少个, 可以查看下面的代码, 本次解法没有进行优化, 会造成
 *       Time Limit Exceeded
 */
public class leetcode_377_CombinationSumIV {
	public int combinationSum4(int[] nums, int target) {
		return getCombinationSum( nums, target );
	}

	public int getCombinationSum (int[] nums, int target) {
		if ( target < 0 ) {
			return 0;
		}

		if ( target == 0 ) {
			return 1;
		}

		int count = 0;
		for ( int i = 0; i < nums.length; i ++ ) {
			count += getCombinationSum(nums, target - nums[i]);
		}

		return count;
	}
}
