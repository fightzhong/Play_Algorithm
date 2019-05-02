import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
     Description:
         Given an array nums of n integers, are there elements a, b, c in nums such that
         a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 
     Example:
        Given array nums = [-1, 0, 1, 2, -1, -4],
        
        A solution set is:
        [
          [-1, 0, 1],
          [-1, -1, 2]
        ]
     
         思想:
                 对所有的元素进行遍历, 每次固定一个元素, 则剩下要求的和是 target = 0 - 元素的值
                  然后维护一个l, r变量, 对当前元素后面的所有元素进行遍历, 每次求得一组和等于target, 则l++, r--到下一对不等于l, r的值
     
 
 
 */

public class leetcode_015_3Sum2 {
    public static void main(String[] args) {
        int[] nums = new int[] {-1,0,1,2,-1,-4};
        System.out.println(new leetcode_015_3Sum2().threeSum(nums));
    }
    
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        ArrayList<List<Integer>> list = new ArrayList<List<Integer>>();
        if (nums.length < 3 || nums[0] > 0) {
            return list;
        }
        
        // 确保至少有三个元素
        for (int i = 0; i < nums.length - 2;) {
            
            int target = 0 - nums[i];            
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                if (nums[l] + nums[r] < target) {
                    l++;
                } else if (nums[l] + nums[r] > target) {
                    r--;
                } else {
                    ArrayList<Integer> arr = new ArrayList<Integer>();
                    arr.add(nums[i]);
                    arr.add(nums[l]);
                    arr.add(nums[r]);
                    list.add(arr);
                    
                    /*
                     * 注意: 要想获得下一组与target相等的元素, l要++一直到下一个与它不同的元素, r--一直到下一个与
                     *         它不同的元素
                     */ 
                    // l一直到最后一个与它相等的元素
                    int curL = nums[l];
                    while (l < r && nums[l] == curL) {
                        l++;
                    }
                    
                    // r--一直到下一个与r不相等的元素, l不用变
                    int curR = nums[r];
                    while (l < r && nums[r] == curR) {
                        r--;
                    }
                }
            }
            
            /**
             * 将i变成最后一个等于nums[i]的值, 因为跟num[i] 相加等于0的情况都在上面考虑完了, 包括
             * 存在两个i的情况, 所以这里可以考虑下一个元素了
             */ 
            int curI = nums[i];
            while (i < nums.length - 2 && curI == nums[i]) {
                i++;
            }
        }
        
        return list;
    }
}





