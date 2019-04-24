import java.util.ArrayList;
import java.util.Arrays;

/**
 	Description:
	 	Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
		Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

 	Example:
		Given nums = [0,0,1,1,1,1,2,3,3],
		Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
 	
 	思路: 
 			<1> 维护一个repeatIndex, 代表了每一类相同元素的第一个元素
 			<2> 维护一个insertIndex, 代表了插入元素的位置, 我们把这个数组在遍历的过程中, 判断一个元素是否符合条件, 符合则将其从前往后插入
 			<3> 维护一个curIndex, 代表了当前遍历的元素
 			
 			repeatIndex初始设为0, 因为索引为1的元素可能会和索引0的元素相同
 			insertIndex初始设为1, 因为我们下一个插入元素的位置一定是索引为1的位置
 			curIndex初始设为1, 索引为0的元素不必考虑了
 */
public class leetcode_080_RemoveDuplicatefromSortedArrayII {
	public static void main(String[] args) {
		int[] arr = new int[] {0,0,1,1,1,1,2,3,3};
		System.out.println(Arrays.toString(arr));
		int result = removeDuplicates(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println(result);
	}
	
	public static int removeDuplicates(int[] nums) {
		int insertIndex = 1;		// 下一个插入的位置
		int repeatIndex = 0;		// 每一类重复元素的起始位置
		int curIndex = 1;
		
		while (curIndex < nums.length) {
			
			// 如果当前元素 == 重复位置的元素, 并且索引差小于2, 说明个数是小于2个的, 则可以将其计算在内
			if (nums[curIndex] == nums[repeatIndex] && (curIndex - repeatIndex) < 2) {
				nums[insertIndex++] = nums[curIndex];
			}
			
			if (nums[curIndex] != nums[repeatIndex]) {
				// 和重复的元素不相等, 则将其放在insertIndex位置, 并将其设为第一个重复的元素
				nums[insertIndex++] = nums[curIndex];
				repeatIndex = curIndex;
			}
			
			curIndex ++;
		}
		
		return insertIndex;
	}
}
