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
// 比第一个版本的快了很多
public class leetcode_219ContainsDuplicateII2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
        
        for (Integer i: map.keySet()) {
            if (map.get(i).size() >= 2) {
                ArrayList<Integer> indexs = map.get(i);
                int l = 0; 
                int r = indexs.size() - 1;
                
                // 找到是否存在两个值的差是小于等于k的
                while (l < r) {
                    if (indexs.get(r) - indexs.get(l) <= k) {
                        return true;
                    }
                    
                    // 如果除了l, r两个元素外,之间还有超过两个元素, 那么就让l++, r--来缩小范围
                    // 如果少于两个元素了, 那么只要让l++去缩小范围就好了
                    if (r - l > 2) {
                        l++;
                        r--;
                    } else {
                        l++;
                    }
                }
            }
        }
        
        return false;
    }
}
