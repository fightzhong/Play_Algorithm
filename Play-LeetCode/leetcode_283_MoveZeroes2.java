import java.util.Arrays;

/**
 	Description:
	 	Given an array nums, write a function to move all 0's to the end of 
	 	it while maintaining the relative order of the non-zero elements.

 	Example:
		Input: [0,1,0,3,12]
		Output: [1,3,12,0,0]
 	
 	思路: (O(n)复杂度!!)
 		将遍历一遍数组, 将所有的非0元素放在前面, 之后将后面的所有变为0
 	特殊处理: 当所有元素都是非0元素时, 应该不进行交换	
 */
public class leetcode_283_MoveZeroes2 {
	public static void main(String[] args) {
		int[] arr = new int[] {0,1,0,3,12};
		moveZeroes(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void moveZeroes (int[] nums) {
		int insertIndex = 0;
		for (int i = 0; i < nums.length; i++) {
			// 只有在遇到非0元素时才要进行交换
			if (nums[i] != 0 ) {
				// 如果交换的是该元素本身, 则直接维护一下insertIndex就好了
				if (i == insertIndex) {
					insertIndex ++;
					continue;
				}
				swap(nums, insertIndex++, i);
			}
		}
	}
	
	public static void swap (int[] nums, int a, int b) {
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}
}
