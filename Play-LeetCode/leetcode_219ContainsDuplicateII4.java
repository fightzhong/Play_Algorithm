import java.util.ArrayList;
import java.util.HashMap;

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
public class leetcode_219ContainsDuplicateII4 {
    public static void main(String[] args) {
        int[] nums = new int[] {1,0,1,1};
        System.out.println(new leetcode_219ContainsDuplicateII4().containsNearbyDuplicate(nums, 1));
    }
    
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        // [i,k + i]中元素个数是k + 1个
        for (int i = 0; i < nums.length; i++) {
           // 当前map中元素个数是少于k+1个的, 所以再加一个元素后, 只可能出现元素个数小于等于k+1的情况,
           // 而小于等于k+1是属于范围内的
           if (map.get(nums[i]) != null) {
               return true;
           }
           map.put(nums[i], 1);
           
           if (map.size() == k + 1) {
               map.remove(nums[i - k]);
           }
        }
        
        return false;
    }
}
