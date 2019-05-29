import java.util.Arrays;

// 动态规划的解法: 自底向上
public class leetcode_064_MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int[][] minSum = new int[grid.length][grid[0].length];
        // 初始化最右下角的值
        minSum[minSum.length - 1][minSum[0].length - 1] = grid[grid.length - 1][grid[0].length - 1];
        
        for ( int i = grid.length - 1; i >= 0; i -- ) {
            for ( int j = grid[0].length - 1; j >= 0; j -- ) {
                if ( i == grid.length - 1 && j == grid[0].length - 1 ) {
                    continue;
                }
                
                int rightMin = j + 1 < minSum[0].length ? minSum[i][j + 1] : Integer.MAX_VALUE;
                int downMin = i + 1 < minSum.length ? minSum[i + 1][j] : Integer.MAX_VALUE;
                minSum[i][j] = grid[i][j] + Math.min( rightMin, downMin );
            }
        }
        
        System.out.println(Arrays.deepToString(minSum));
        
        return minSum[0][0];
    }   
}
