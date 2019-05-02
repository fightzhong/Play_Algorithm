/**
      Description:
          Given an array of integers and an integer k, find out whether there are two distinct indices i and j 
          in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
          
      Example 1:
            Input: nums = [1,2,3,1], k = 3
            Output: true
            
      Example 2:
            Input: nums = [1,0,1,1], k = 1
            Output: true    
 */
// 时间复杂度过高, 需要进行优化
public class leetcode_219ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            // 找到与l位置相同的值
            while (l < r && nums[l] != nums[r]) {
                r--;
            }
            
            if (l < r && r - l <= k) {
                return true;
            }
            
            // 如果l < r, 说明此时找到了与l位置相同的值, 那么还需要考虑是否还存在相同的值, 则只需要r--就好了
            if (l < r) {
                r--;
            } else {
                l++;
                r = nums.length - 1;
            }
        }
        
        return false;
    }
}
