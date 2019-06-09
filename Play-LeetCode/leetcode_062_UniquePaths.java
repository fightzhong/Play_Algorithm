import java.util.Arrays;

public class leetcode_062_UniquePaths {
    private int[][] isVisited;
    int m;
    int n;
    
    public int uniquePaths(int m, int n) {
        this.m = m;
        this.n = n;
        isVisited = new int[m][n];
        for ( int[] arr: isVisited ) {
            Arrays.fill( arr, Integer.MAX_VALUE );
        }
        
        return getPaths(0, 0);
    }
    
    // x, y表示当前的坐标
    public int getPaths (int x, int y) {
        if ( x >= m || y >= n ) {
            return 0;
        }
        
        if ( x == m - 1 && y == n - 1 ) {
             return 1;
        }
        
        if ( isVisited[x][y] != Integer.MAX_VALUE ) {
            return isVisited[x][y];
        }
        
        // 往右走
        int rightCount = getPaths( x, y + 1 );
        // 往左走
        int leftCount = getPaths( x + 1, y );
        
        isVisited[x][y] = leftCount + rightCount;
        
        return isVisited[x][y];
    }
}
