import java.util.TreeSet;

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
          设置一个变量l = 0开始, 然后对数组从索引为0的位置开始遍历, 不停的去扩充区间[l, i], 每扩容一个
          就判断这个新增加的元素与前面到l位置为止的元素是否存在满足差的绝对值小于等于t的情况,与第一版本的不同的是
          这个判断我们需要对新增的元素i, 查找是否存在 一个[nums[i] - t] -> nums[i] + t这个区间的值存在于[l,i]区间
      
      利用TreeSet的ceil(e): 查找集合中第一个元素大于等于e的
                floor(e): 查找集合中最大的一个元素小于等于e的
    TreeSet是有序的, 那么其第一个大于等于e其实就是所有大于等于e的元素中最小的那个元素          
          
   注意事项: 这个差的绝对值有可能会溢出整型, 所以需要对这个值用long来保存       
 */
public class leetcode_220_ContainsDuplicateIII2 {
    public static void main(String[] args) {
        int[] nums = new int[] {1,5,9,1,5,9};
        int k = 2;
        int t = 3;
        System.out.println(new leetcode_220_ContainsDuplicateIII2().containsNearbyAlmostDuplicate(nums, k, t));
    }
    
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 如果k == 0, 说明i, j两个索引是相同的, 那么此时这两个索引的差一定为0, 如果t不为0, 那么是一定找不到的
        if (k == 0 && t != 0) {
            return false;
        }
        
        int l = 0;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 查找集合中是否存在值大于等于nums[i] - t 或者 小于等于nums[i] + t
            if (set.ceiling((long)nums[i] - t) != null 
                && set.ceiling((long)nums[i] - t) <= (long)nums[i] + t) {
                return true;
            }
            
            set.add((long)nums[i]);
            
            // 如果l到i之间的元素个数已经达到了k的标准, 那么就要使得l++来缩小, 并且删除集合中nums[l]的值
            if (i - l == k) {
                set.remove((long)nums[l++]);
            }
        }
        
        return false;
    }
}
