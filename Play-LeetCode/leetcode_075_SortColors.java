import java.util.Arrays;

/**
	Description:
		Given an array with n objects colored red, white or blue, sort them in-place so that objects
		of the same color are adjacent, with the colors in the order red, white and blue.

		Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

	Example:
		Input: [2,0,2,1,1,0]
		Output: [0,0,1,1,2,2]
	
	思路: 
		将等于2的值放在末尾, 同时遍历的索引应该 i--, 因为最后一个元素放在当前位置, 该元素是没有被检查的

		在前面维护一个变量 l, 该变量表示最后一个0元素的位置, 初始为 -1, 说明没有, 一旦遍历到了0元素, 就将其放在l + 1的位置, 同时维护 l 的值

		如果遍历到了元素1, 则直接继续遍历下一个元素
 */
public class leetcode_075_SortColors {
	public static void main(String[] args) {
		int[] arr = new int[] {2,0,1};
		System.out.println(Arrays.toString(arr));
		sortColors(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void sortColors(int[] nums) {
		int l = -1;
		int r = nums.length;
		
		for (int i = 0; i < r; i++) {
			if (nums[i] == 0) {
				swap(nums, ++l, i);
			} else if (nums[i] == 2) {
				swap(nums, --r, i--);
			}
		}
   }
	
	public static void swap (int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
