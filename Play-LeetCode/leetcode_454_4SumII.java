import java.util.Arrays;

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
 */

// 虽然能通过, 但是时间复杂度过高
public class leetcode_454_4SumII {
    public static void main(String[] args) {
        int[] A = new int[] {0,1,-1};
        int[] B = new int[] {-1,1,0};
        int[] C = new int[] {0,0,1};
        int[] D = new int[] {-1,1,1};
        
        System.out.println(new leetcode_454_4SumII().fourSumCount(A, B, C, D));
    }
    
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Arrays.sort(C);
        Arrays.sort(D);
        
        int tuples = 0;
        for (int i = 0; i < A.length; i++) {
            
            for (int j = 0; j < B.length; j++) {
               int target = 0 - A[i] - B[j];
               int l = 0;
               int r = D.length - 1;
               
               while (l <= C.length -1 && r >= 0) {
                   if (C[l] + D[r] > target) {
                       r--;
                   } else if (C[l] + D[r] < target) {
                       l++;
                   } else {
                       
                       // 获取与当前l, r相同的值的个数
                       int lNum = 1;
                       int rNum = 1;
                       while (l + 1 <= C.length - 1 && C[l] == C[l + 1]) {
                           l++;
                           lNum++;
                       }
                       
                       
                       while (r - 1 >= 0 && D[r] == D[r - 1]) {
                           r--;
                           rNum++;
                       }
                       tuples += lNum * rNum;

                       // 将i, j移到下一个不等于其本身值的位置
                       r--;
                       l++;
                   }
               }
            }
        }
        
        return tuples;
    }
}

