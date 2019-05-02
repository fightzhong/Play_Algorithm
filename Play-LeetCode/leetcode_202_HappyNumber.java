import java.util.HashSet;
import java.util.Set;

/**
    Description:
        Write an algorithm to determine if a number is "happy".
        A happy number is a number defined by the following process: Starting with any positive integer,
        replace the number by the sum of the squares of its digits, and repeat the process until the number
        equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. 
        Those numbers for which this process ends in 1 are happy numbers.

    Example: 
        Input: 19
        Output: true
        
        Explanation: 
        1^2 + 9^2 = 82
        8^2 + 2^2 = 68
        6^2 + 8^2 = 100
        1^2 + 0^2 + 0^2 = 1
 
 */

public class leetcode_202_HappyNumber {
    public static void main(String[] args) {
        System.out.println(new leetcode_202_HappyNumber().getSum(1));
        System.out.println(new leetcode_202_HappyNumber().getSum(82));
        System.out.println(new leetcode_202_HappyNumber().getSum(68));
        System.out.println(new leetcode_202_HappyNumber().getSum(100));
        
    }
    public boolean isHappy(int n) {
        Set<Integer> hashSet = new HashSet<>();
        
        while (true) {
            if (hashSet.contains(n)) {
               return false;
            } else {
                hashSet.add(n);
            };
            n = getSum(n);
            if (n == 1) {
                return true;
            }
        }
    }
    
    public int getSum (int i) {
        int[] nums = getAllNum(i);
        int sum = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j] * nums[j];
        }
        
        return sum;
    }
    
    public int[] getAllNum (int i) {
        String s = Integer.toString(i);
        int[] result = new int[s.length()];
        for (int j = 0; j < result.length; j++) {
            result[j] = s.charAt(j) - 48;
        }
       
        return result;
    }
}

