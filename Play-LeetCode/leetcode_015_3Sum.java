import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
 
 */

// time limit exceeded: 超时了, 不能用这种方法(思想, 固定一个数, 然后通过two sum的方式进行查找另外两个数)
public class leetcode_015_3Sum {
    public static void main(String[] args) {
        int[] nums = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        System.out.println(new leetcode_015_3Sum().threeSum(nums));
    }
    
    public List<List<Integer>> threeSum(int[] nums) {
        // 首先获取数组nums中每一个元素相加为0的值
        int[] sumZero = new int[nums.length];
        for (int i = 0; i < sumZero.length; i++){
            sumZero[i] = 0 - nums[i];
        }
        
        List<List<Integer>> list = new ArrayList<>();
        
    // 接下来要从根据sumZero中的每一个值去查找相加为该值的, 有一个前提!任何一个索引都不能出现当前值索引
        for (int j = 0; j < sumZero.length; j++){
            int sum = sumZero[j];       // 求和的值
            //  求得在nums数组中任意两个值相加等于sum
            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            for (int i = j + 1; i < nums.length; i++) {
               if (map.get(nums[i]) == null) {
                   // 存放的都是值等于nums[i]的索引
                   ArrayList<Integer> arr = new ArrayList<Integer>();   
                   arr.add(i);
                   map.put(nums[i], arr);
               } else {
                   map.get(nums[i]).add(i);
               } 
            } 
            
            for (int i = j + 1; i < nums.length; i++) {
                if (map.get(sum - nums[i]) != null){
                    ArrayList<Integer> arr = map.get(sum - nums[i]);
                    for (Integer n: arr){
                        if (n > i) {
                            ArrayList<Integer> a = new ArrayList<Integer>();
                            a.add(nums[i]);
                            a.add(nums[j]);
                            a.add(nums[n]);
                            a.sort((k, b) -> {
                                return k.compareTo(b);
                            });
                            if (!list.contains(a)) {
                                list.add(a);
                            }
                        }
                    }
                }
            }
        }
        
        return list;
    }
}

