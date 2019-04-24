/**
 	Description:
	 	Given an array of n positive integers and a positive integer s, find the minimal
	 	length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

 	Example: 
    Input: s = 7, nums = [2,3,1,2,4,3]
    Output: 2
    Explanation: the subarray [4,3] has the minimal length under the problem constraint.
    
    暴力解法:
     维护两个指针, 都从左边开始, 当指针内的结果的值小于s时, 移动右边的指针, 使得结果增大, 当指针内的结果大于s时, 
   移动左边的指针, 使得结果减小, 从而不停的获取了所有的连续子数组, 即连续子区间
 */

public class leetcode_209_MinimumSizeSubarraySum2 {
  public static void main(String[] args) {
    int[] nums = new int[] {1, 2, 3, 4, 5};
//    int[] nums = new int[] {2,3,1,2,4,3};
    int result = new leetcode_209_MinimumSizeSubarraySum2().minSubArrayLen(15, nums);
    System.out.println(result);
  }
  
  public int minSubArrayLen(int s, int[] nums) {
    int point1 = 0;
    int point2 = 0;
    int result = nums[point1];
    int len = nums.length + 1;
    
    // point1 <= point2 是为了处理一个元素大于s的情况 
    while (point1 <= point2) {
      
      // 如果结果大于s, 则此时看看需不需要更新元素的长度, 以及减去左指针指向的元素, 同时左指针往后移动一个位置
      if (result >= s) {
        if (point2 - point1 + 1 <= len) {
          len = point2 - point1 + 1;
        }
        result -= nums[point1++];
      } else {
        // 结果小于s, 则右指针往后移动一个位置使得结果能够增大
        point2++;
        // 边界处理, 可以先略过
        if (point2 >= nums.length) {
          break;
        }
        result += nums[point2];
      }
      
    }
      
    return len > nums.length ? 0 : len;
  }
  
}
