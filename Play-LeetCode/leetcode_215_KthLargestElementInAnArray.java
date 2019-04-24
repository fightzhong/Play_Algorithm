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

public class leetcode_215_KthLargestElementInAnArray {
	public static void main(String[] args) {
//		int[] nums = new int[] {3,2,1,5,6,4};
		int[] nums = new int[] {3,2,3,1,2,4,5,5,6};
	
		System.out.println(findKthLargest(nums, 4));
	}
	
	public static int findKthLargest(int[] nums, int k) {
      int index = nums.length - k;			// 第k大的元素的索引位置
		quickSort(nums, 0, nums.length - 1);
		System.out.println(Arrays.toString(nums));
		return nums[index];
   }
	
	// 对nums数组[l, r]闭区间的元素进行快速排序
	public static void quickSort (int[] nums, int l, int r) {
		if (l >= r) {
			return;
		}
		
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
		
		quickSort(nums, l, left - 1);
		quickSort(nums, right, r);
	}
	
	public static void swap (int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}







