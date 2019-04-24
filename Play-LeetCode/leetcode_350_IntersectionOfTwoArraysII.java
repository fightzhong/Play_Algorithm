import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
     Description:
         Given two arrays, write a function to compute their intersection.
     
     Example 1:
        Input: nums1 = [1,2,2,1], nums2 = [2,2]
        Output: [2, 2]
     
     Example 2:
        Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
        Output: [4, 9]
 */
public class leetcode_350_IntersectionOfTwoArraysII {
    public static void main(String[] args) {
        int[] nums1 = new int[] {1,2,2,1};
        int[] nums2 = new int[] {2,2};
        System.out.println(Arrays.toString(intersection(nums1, nums2)));
    }
    
    public static int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<Integer, Integer>(); 
        for (int i: nums1) {
            Integer a = map1.get(i);
            if (a != null) {
                map1.put(i, a + 1);
            } else {
                map1.put(i, 1);
            }
        }
        
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i: nums2) {
            Integer a = map1.get(i);
            if (a != null && a != 0) {
                arr.add(i);
                map1.put(i, a - 1);
            }
        }
        
        int[] result = new int[arr.size()];
        int index = 0;
        for (Integer i: arr) {
            result[index++] = i;
        }
        
        return result;
    }
}
