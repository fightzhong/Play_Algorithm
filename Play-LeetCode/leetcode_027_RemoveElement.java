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
 	
 	删除指定元素, 数组不需要进行改变(将元素放在后面也可以), 返回删除后新的长度
 */
public class leetcode_027_RemoveElement {
	public int removeElement(int[] nums, int val) {
		int insertIndex = -1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != val) {
				nums[++insertIndex] = nums[i]; 
			}
		}
		
		return insertIndex + 1;
	}
}
