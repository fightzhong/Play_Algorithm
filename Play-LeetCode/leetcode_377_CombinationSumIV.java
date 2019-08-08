import java.util.ArrayList;
import java.util.Arrays;

public class leetcode_377_CombinationSumIV {
	public static void main (String[] args) {
		int[] nums = new int[]{1, 2, 3};
		int target = 4;
		System.out.println(new leetcode_377_CombinationSumIV().combinationSum4( nums, target ));
	}

	private int count = 0;
	public int combinationSum4(int[] nums, int target) {
		Arrays.sort( nums );
		findCombinationSum( nums, 0, target);
		return 1;
	}

	private void findCombinationSum(int[] nums, int index, int target) {
		if ( index >= nums.length || target < 0 ) {
			return;
		}

		if ( nums[index] == target ) {
			count++;
			return;
		}

		// 选择当前元素, 并从下一个元素开始
		findCombinationSum( nums, index + 1, target - nums[index] );

		// 选择当前元素, 并从当前元素开始
		findCombinationSum( nums, index, target - nums[index] );

		// 不选择当前元素, 并从下一个元素开始
		findCombinationSum( nums, index + 1, target );

	}
}
