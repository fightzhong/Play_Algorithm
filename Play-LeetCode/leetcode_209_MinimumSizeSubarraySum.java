/**
 	Description:
	 	Given an array of n positive integers and a positive integer s, find the minimal
	 	length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

 	Example: 
    Input: s = 7, nums = [2,3,1,2,4,3]
    Output: 2
    Explanation: the subarray [4,3] has the minimal length under the problem constraint.
    
    暴力解法:
       依次获取每一个区间的值即可, 大于s则继续判断长度是否小于len, 小于则更新len
 */

public class leetcode_209_MinimumSizeSubarraySum {
  public static void main(String[] args) {
//    int[] nums = new int[] {1, 2, 3, 4, 5};
    int[] nums = new int[] {10, 2, 3};
    int result = new leetcode_209_MinimumSizeSubarraySum().minSubArrayLen(6, nums);
    System.out.println(result);
  }
  
  public int minSubArrayLen(int s, int[] nums) {
    int len = nums.length + 1;
    boolean hasLen = false;
    
    for (int i = 0; i < nums.length; i++) {
      int result = nums[i];
      if (result >= s) {
        len = 1;
        hasLen = true;
        break;
      }
      for (int j = i + 1; j < nums.length; j++) {
        result += nums[j];
        if (result >= s) {
          if ((j - i + 1) < len) {
            len = j - i + 1;
            hasLen = true;
          }
          break;
        }
      }
    }
    
    return hasLen ? len : 0;
  }
  
}
