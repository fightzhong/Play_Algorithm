import java.util.Arrays;

// 动态规划的解法: 自底向上
public class leetcode_064_MinimumPathSum2 {
    private int[][] sum;
    public int minPathSum(int[][] grid) {
        sum = new int[grid.length][grid[0].length];
        for ( int[] arr: sum ) {
            Arrays.fill( arr, -1 ); // 因为都是非负数, 所以用-1取代未求得的值是可以的
        }
        
        return getMinSum( grid, 0, 0 );
    }
    
    public int getMinSum (int[][] grid, int row, int col) {
        if ( row == grid.length - 1 && col == grid[0].length - 1 ) {
            return grid[row][col];
        }
        
        int rightSum = col + 1 < grid[0].length ? sum[row][col + 1] == -1 ? getMinSum( grid, row, col + 1 ) : sum[row][col + 1] : Integer.MAX_VALUE;
        int downSum = row + 1 < grid.length ? sum[row + 1][col] == -1 ? getMinSum( grid, row + 1, col ) : sum[row + 1][col] : Integer.MAX_VALUE;
        sum[row][col] = grid[row][col] + Math.min( rightSum, downSum );
        
        return sum[row][col];
    }
}
