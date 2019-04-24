import java.util.ArrayList;
import java.util.Arrays;

/**
 	Description:
	 	Given an array nums and a value val, remove all instances of that value in-place and return the new length.
		Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
		The order of elements can be changed. It doesn't matter what you leave beyond the new length.

 	Example:
		Given nums = [3,2,2,3], val = 3,
		Your function should return length = 2, with the first two elements of nums being 2.
 
 */
public class leetcode_027_RemoveElement2 {
	public int removeElement(int[] nums, int val) {
		int insertIndex = -1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != val) {
				if (i != insertIndex + 1) {
					nums[++insertIndex] = nums[i]; 
				} else {
					insertIndex++;
				}
			}
		}
		
		return insertIndex + 1;
	}
}
