import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
     Description:
         Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. 
         Return the sum of the three integers. You may assume that each input would have exactly one solution.
 
     Example:
        Given array nums = [-1, 2, 1, -4], and target = 1.
        The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
         
         思路：
             查找三个数相加最接近于target的和, 类似于三个数相加等于target的, 不同的是判断条件应该变为
        Math.abs(a + b + c - target) <= xxx; 
            这里没有要求这三个数是连续的  
 */

public class leetcode_016_3SumClosest {
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,4,8,16,32,64,128};
        int target = 82;
        System.out.println(new leetcode_016_3SumClosest().threeSumClosest(nums, target));
    }
    
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE;     // 接近程度设为最大, 当有小于这个接近程度的时候, 就更改closest的值
        int result = Integer.MAX_VALUE;
        // 确保至少有三个数, 所以i的值最多能取到nums.length - 2
        for (int i = 0; i < nums.length - 2;) {
            int l = i + 1;
            int r = nums.length - 1;
            
            while (l < r) {
                int sum = nums[l] + nums[r] + nums[i];
                // 求得sum 与target的接近程度, 只有接近程度小于原来的接近程度, 才去更新closest和sum的值
                if (Math.abs(sum - target) < closest) {
                   closest = Math.abs(sum - target);
                   result = sum; 
                }
                
                // 如果sum > target, 那么为了更加的接近target, 则需要减小sum的值
                if (sum > target) {
                    r--;
                } else if (sum < target){
                    l++;
                } else {
                    // 如果sum == target, 那么此时应该直接返回当前的sum的值了, 此时都相等了
                    return sum;
                }
            }
            
            // 找到下一个不等于i的值, 防止重复考虑该元素
            while (i < nums.length - 2 && nums[i] == nums[i + 1]) {
                i++;
            }
            i++;
        }
        
        return result;
    }
}




