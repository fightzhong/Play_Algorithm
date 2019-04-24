import java.util.Arrays;

/**
 	Description:
 		Find the kth largest element in an unsorted array.
 		Note that it is the kth largest element in the sorted order, not the kth distinct element.
 	
 	Example 1:
		Input: [3,2,1,5,6,4] and k = 2
		Output: 5
 	
 	Example 2:
		Input: [3,2,3,1,2,4,5,5,6] and k = 4
		Output: 4
 
 */

public class leetcode_215_KthLargestElementInAnArray2 {
	public static void main(String[] args) {
		int[] nums = new int[] {3,2,1,5,6,4};
//		int[] nums = new int[] {3,2,3,1,2,4,5,5,6};
	
		System.out.println(findKthLargest(nums, 2));
	}
	
	public static int findKthLargest(int[] nums, int k) {
      int index = nums.length - k;			// 第k大的元素的索引位置
		return quickSort(nums, 0, nums.length - 1, index);
   }
	
	// 对nums数组[l, r]闭区间的元素进行快速排序
	public static int quickSort (int[] nums, int l, int r, int index) {
		int target = nums[l];
		int left = l;
		int right = r + 1;
		
		for (int i = l + 1; i < right;) {
			if (nums[i] > target) {
				swap(nums, i, --right);
			} else if (nums[i] < target) {
				swap(nums, i++, ++left);
			} else {
				i++;
			}
		}
		swap(nums, l, left);
		
		// [l, left - 1] < target, [right, r] > target, [left, right) == target
		if (index <= left - 1) {
			return quickSort(nums, l, left - 1, index);
		} else if (index >= right) {
			return quickSort(nums, right, r, index);
		} else {
			return nums[left];
		}
		
	}
	
	public static void swap (int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}







