import java.util.ArrayList;
import java.util.Arrays;

/**
 	Description:
	 	Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
		Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

 	Example:
		Given nums = [0,0,1,1,1,1,2,3,3],
		Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
 	
 */
public class leetcode_080_RemoveDuplicatefromSortedArrayII2 {
	public static void main(String[] args) {
		int[] arr = new int[] {0,0,1,1,1,1,2,3,3};
		System.out.println(Arrays.toString(arr));
		int result = removeDuplicates(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println(result);
	}
	
	public static int removeDuplicates(int[] nums) {
		int insertIndex = 0;		// 下一个插入的位置
		
		for (int num: nums) {
         if (insertIndex < 2 || num > nums[insertIndex - 2]){
             nums[insertIndex++] = num;
         }
       }
		
		return insertIndex;
	}
}
