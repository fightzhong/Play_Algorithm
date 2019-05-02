import java.util.Arrays;
import java.util.HashMap;

/**
 	Description:
	 	Given an array of integers, return indices of the two numbers such that they add up to a specific target.
        You may assume that each input would have exactly one solution, and you may not use the same element twice.

 	Example 1:
        Given nums = [2, 7, 11, 15], target = 9,
        Because nums[0] + nums[1] = 2 + 7 = 9,
        return [0, 1].. 
 */

public class leetcode_001_TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[] {3, 42, 66, 42, 6};
        System.out.println(Arrays.toString(new leetcode_001_TwoSum().twoSum(nums, 84)));
    }
    
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) != null && (map.get(target - nums[i])) != i) {
                return new int[] {i, map.get(target - nums[i])};
            }
        }

        return null;
    }
}


