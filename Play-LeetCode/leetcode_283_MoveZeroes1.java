import java.util.Arrays;

/**
 	Description:
	 	Given an array nums, write a function to move all 0's to the end of 
	 	it while maintaining the relative order of the non-zero elements.

 	Example:
		Input: [0,1,0,3,12]
		Output: [1,3,12,0,0]
 
 */
public class leetcode_283_MoveZeroes1 {
	public static void main(String[] args) {
		int[] arr = new int[] {0,1,0,3,12};
		moveZeroes(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void moveZeroes (int[] nums) {
      int tail = nums.length - 1; // 数值0需要被放在尾部的位置
      int nextZero = -1;			// 下一个0的位置
      int index = 0;					// 遍历数组的当前位置
      
      // 对数组进行遍历
      while (index < tail) {
      	// 如果出现了0, 那么就对0以后的元素进行遍历, 同时记录下一个0的位置
      	if (nums[index] == 0) {
      		for (int j = index; j < tail; j++) {
      			// 记录下一个0的位置
      			if (nextZero == -1 && nums[index] == 0) {
      				nextZero = j;
      			}
      			// 每次与后面的值交换位置
      			swap(nums, j, j + 1);
      		}
      		
      		// 交换完成后, tail应该减一, 使其成为下一个数值0应该插入的位置
      		tail -= 1;
      		// 如果nextZero在上一次循环中查找到了下一个0, 则直接将index设置为nextZero, 否则, 没有0了, 直接退出循环
      		if (nextZero == -1) {
      			break;
      		}
      		index = nextZero;
      		nextZero = -1;
      	} else {
      		index ++;
      	}
      }
   }
	
	public static void swap (int[] nums, int a, int b) {
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}
}
