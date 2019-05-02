import java.util.Arrays;
import java.util.HashMap;

/**
     Description:
         Given four lists A, B, C, D of integer values
         compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
         
         To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. 
         All integers are in the range of -2^28 to 2^28 - 1 and the result is guaranteed to be at most 2^31 - 1.
     
     Example:
        Input:
        A = [ 1, 2]
        B = [-2,-1]
        C = [-1, 2]
        D = [ 0, 2]
        
        Output:
        2
        
        Explanation:
        The two tuples are:
        1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
        2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
        
     注意: 这里利用map记录个数, 之所以可以这样解决, 是因为题目运行重复的元素出现   
 */

// time complexity: O(n^2)
public class leetcode_454_4SumII2 {
    public static void main(String[] args) {
        int[] A = new int[] {0,1,-1};
        int[] B = new int[] {-1,1,0};
        int[] C = new int[] {0,0,1};
        int[] D = new int[] {-1,1,1};
        
        System.out.println(new leetcode_454_4SumII2().fourSumCount(A, B, C, D));
    }
    
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int tuples = 0;
        
        HashMap<Integer, Integer> count = new HashMap<>();
        // 获取数组C, D中元素相加后值相同情况的个数
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sum = C[i] + D[j];
                if (count.get(sum) == null) {
                    count.put(sum, 1);
                } else {
                    count.put(sum, count.get(sum) + 1);
                }
            }
        }
        
        for (int i = 0; i < A.length; i++) {
           for (int j = 0; j < B.length; j++) {
               int target = 0 - A[i] - B[j];
               if (count.get(target) != null) {
                   tuples += count.get(target);
               }
           }
        }
        
        return tuples;
    }
}

