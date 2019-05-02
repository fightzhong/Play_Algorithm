import java.util.HashSet;

/**
      Description:
          Given an array of integers, find out whether there are two distinct indices i and j 
          in the array such that the absolute difference between nums[i] and nums[j] is 
          at most t and the absolute difference between i and j is at most k.
      
      Example 1:
        Input: nums = [1,2,3,1], k = 3, t = 0
        Output: true
      
      Example 2:
        Input: nums = [1,0,1,1], k = 1, t = 2
        Output: true
        
      思路:
          设置一个变量l = 0开始, 然后对数组从索引为1的位置开始遍历, 不停的去扩充区间[l, i], 每扩容一个
          就判断这个新增加的元素与前面到l位置为止的元素是否存在满足差的绝对值小于等于t的情况, 也就是说, 每次
          添加一个元素到区间, 就要对这个区间从l到i - 1的元素进行遍历, 查看是否存在与i元素差的绝对值满足情况的
          一旦区间达到了k的要求, 就让l++, i++来将区间往后扩容 
          
   注意事项: 这个差的绝对值有可能会溢出整型, 所以需要对这个值用long来保存       
 */
public class leetcode_220_ContainsDuplicateIII {
    public static void main(String[] args) {
        int[] nums = new int[] {1,2};
        int k = 0;
        int t = 1;
        System.out.println(new leetcode_220_ContainsDuplicateIII().containsNearbyAlmostDuplicate(nums, k, t));
    }
    
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 如果k == 0, 说明i, j两个索引是相同的, 那么此时这两个索引的差一定为0, 如果t不为0, 那么是一定找不到的
        if (k == 0 && t != 0) {
            return false;
        }
        
        int l = 0;
        for (int i = 1; i < nums.length; i++) {
            // 计算当前i 与 [l,i)之间所有数的差, 并查看这个值是否满足条件
            for (int j = i - 1; j >= l; j--) {
                long result = Math.abs((long)nums[i] - nums[j]);
                if (result <= t) {
                    return true;
                }
            }
            
            // 如果l到i之间的元素个数已经达到了k的标准, 那么就要使得l++来缩小, 从而下一次i++又是一个满足条件的区间
            if (i - l == k) {
                l++;
            }
        }
        
        return false;
    }
}
