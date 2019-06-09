import java.util.Arrays;

public class leetcode_063_UniquePathsII {
    private int[][] isVisited;
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        if ( obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1 ) {
            return 0;
        }
        
        isVisited = new int[m][n];
        for ( int[] arr: isVisited ) {
            Arrays.fill( arr, Integer.MAX_VALUE );
        }
        isVisited[m - 1][n - 1] = 1;
        
        for ( int i = m - 1; i >= 0; i -- ) {
            for ( int j = n - 1; j >= 0; j -- ) {
                if ( i == m - 1 && j == n - 1 ) {
                    continue;
                }
                
                if ( obstacleGrid[i][j] == 1 ) {
                    isVisited[i][j] = 0;
                    continue;
                }
                
                int rightCount = j + 1 < n ? ( isVisited[i][j + 1] == Integer.MAX_VALUE ? 0 : isVisited[i][j + 1] ) : 0;
                int leftCount = i + 1 < m ? ( isVisited[i + 1][j] == Integer.MAX_VALUE ? 0 : isVisited[i + 1][j] ) : 0;
                isVisited[i][j] = rightCount + leftCount;
            }
        }
        return isVisited[0][0];
    }
}
