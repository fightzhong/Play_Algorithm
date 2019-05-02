import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
     Description:
         Given an array nums of n integers and an integer target, are there elements a, b, c, 
         and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
      
     Example:
        Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
        A solution set is:
        [
          [-1,  0, 0, 1],
          [-2, -1, 1, 2],
          [-2,  0, 0, 2]
        ] 
  
 
 */

public class leetcode_018_4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 对数组进行排序
        Arrays.sort(nums);
        ArrayList<List<Integer>> list = new ArrayList<>();
        
        // 从左到右固定一个值
        for (int i = 0; i < nums.length - 3; ) {
            int target1 = target - nums[i];
            // 求[i+1, nums.length - 1]范围内三个数的和为t的
            for (int j = i + 1; j < nums.length - 2;) {
                // 固定当前i的值并求得两个数的和为tt的
                int target2 = target1 - nums[j];
                int l = j + 1;
                int r = nums.length - 1;
                while (l < r) {
                    if (nums[l] + nums[r] > target2) {
                        r--;
                    } else if (nums[l] + nums[r] < target2) {
                        l++;
                    } else {
                        // 当前这对值是满足要求的, 则应该将其放入数组中
                        ArrayList<Integer> arr = new ArrayList<>();
                        arr.add(nums[i]);
                        arr.add(nums[j]);
                        arr.add(nums[l]);
                        arr.add(nums[r]);
                        list.add(arr);
                        
                        // l + r == target2, 为了防止重复添加, l, r应该变为下一个不等于l, r的值
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        l++;
                        
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        r--;
                    }
                }
                
                // 一轮循环结束后, 已经找到了固定点j, 剩下两个数和为target2的集合, 此时固定点应该往后遍历到下一个不等于其的值
                while (j < nums.length - 2 && nums[j] == nums[j + 1]) {
                    j++;
                }
                j++;
            }
            
            // 一轮循环结束后, 已经找到了固定点i, 剩下三个数和为target1的集合, 此时固定点应该往后遍历到下一个不等于其的值
            while (i < nums.length - 3 && nums[i] == nums[i + 1]) {
                i++;
            }
            i++;
        }
        
        return list;
    }
}