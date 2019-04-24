import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
     Description:
         Given two arrays, write a function to compute their intersection.
     
     Example 1:
        Input: nums1 = [1,2,2,1], nums2 = [2,2]
        Output: [2]
     
     Example 2:
        Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
        Output: [9,4]
 */
public class leetcode_349_IntersectionOfTwoArrays {
    public static void main(String[] args) {
        int[] nums1 = new int[] {4,9,5};
        int[] nums2 = new int[] {9,4,9,8,4};
        System.out.println(Arrays.toString(intersection(nums1, nums2)));
    }
    
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> n1 = new HashSet<Integer>();
        for (int i: nums1) {
            n1.add(i);
        }
        
        Set<Integer> n2 = new HashSet<Integer>();
        for (int i: nums2) {
           if (n1.contains(i)) {
               n2.add(i);
           }
        }
        
        int[] result = new int[n2.size()];
        int index = 0;
        for (Integer i: n2) {
            result[index++] = i;
        }
        
        return result;
    }
}
