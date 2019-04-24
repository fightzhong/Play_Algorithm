import java.util.Arrays;

/**
	Description:
		Given an array of integers that is already sorted in ascending order, 
		find two numbers such that they add up to a specific target number.

		The function twoSum should return indices of the two numbers such that
		they add up to the target, where index1 must be less than index2.

	Example:
		Input: numbers = [0,2,3,7,8,11,15], target = 9
		Output: [2,4]
		Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.	
 */
public class leetcode_167_TwoSumII_InputArrayIsSorted {
	public static void main(String[] args) {
//		int[] arr = new int[] {2,7,11,15};		// 9
//		int[] arr = new int[] {5,25,75};			// 100
		int[] arr = new int[] {-1, 0};			// -1
		System.out.println(Arrays.toString(twoSum(arr, -1)));
	}
	
	public static int[] twoSum(int[] numbers, int target) {
		int min = 0;										// 区间[l, r]中的最小值
		int max = numbers.length - 1;					// 区间[l, r]中的最大值
		int[] result; 										// 结果数组
		
		while (min < max) {
			if (numbers[min] + numbers[max] > target) {
				max--;
			} else if (numbers[min] + numbers[max] < target) {
				min++;
			} else {
				result = new int[] {min + 1, max + 1};
				return result;
			}
		}
		
		// 到了此时应该是min == max, 此时应该判断是否该元素的两倍是target
		if (numbers[min] * 2 == target) {
			result = new int[] {min + 1, min + 1};
		} else {
			result = null;
		}
		
		return result;
   }
}
