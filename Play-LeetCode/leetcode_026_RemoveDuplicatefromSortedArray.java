import java.util.ArrayList;
import java.util.Arrays;

/**
 	Description:
	 	Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
		Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

 	Example:
		Given nums = [1,1,2],
		Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 	
 	
 */
public class leetcode_026_RemoveDuplicatefromSortedArray {
	public int removeDuplicates(int[] nums) {
      int normalIndex = 0;	// 代表最后一个非重复元素, 其前面不会有重复元素, 其后一个位置即下一个正常元素存放的位置
      int index = 1;
      
      while (index < nums.length) {
      	// 如果当前元素和最后一个非重复元素不相等, 那么就将当前元素设为最后一个非重复元素
      	if (nums[index] != nums[normalIndex]) {
      		nums[++normalIndex] = nums[index];
      	}
      	// 优化: 针对于大量不相同的元素来说, 应该不将他们进行重新赋值, 而是直接将其设为最后一个非重复元素
//      	if (nums[index] != nums[normalIndex] && (++normalIndex != index)) {
//      		nums[normalIndex] = nums[index];
//      	}
      	index++;
      }
      return normalIndex + 1;
   }
}
