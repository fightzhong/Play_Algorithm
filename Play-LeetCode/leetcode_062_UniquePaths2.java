import java.util.Arrays;

public class leetcode_062_UniquePaths2 {
    public static void main(String[] args) {
        System.out.println( new leetcode_062_UniquePaths2().uniquePaths(3, 2) );
    }
    
    private int[][] isVisited;
    public int uniquePaths(int m, int n) {
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
                
                int rightCount = j + 1 < n ? ( isVisited[i][j + 1] == Integer.MAX_VALUE ? 0 : isVisited[i][j + 1] ) : 0;
                int leftCount = i + 1 < m ? ( isVisited[i + 1][j] == Integer.MAX_VALUE ? 0 : isVisited[i + 1][j] ) : 0;
                isVisited[i][j] = rightCount + leftCount;
            }
        }
        
        return isVisited[0][0];
    }
}
