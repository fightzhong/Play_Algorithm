import java.util.Arrays;

public class leetcode_200_NumberofIslands {
    public static void main(String[] args) {
        char[][] grid = new char[][] { {'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'} };
        System.out.println( new leetcode_200_NumberofIslands().numIslands( grid ) );
    }
    
    private boolean[][] isVisited;
    private int[][] data = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    
    public int numIslands(char[][] grid) {
        isVisited = new boolean[grid.length][grid[0].length];
        for ( boolean[] b: isVisited ) {
            Arrays.fill( b, false );
        }
        
        int count = 0;
        if ( grid.length == 0 ) {
            return count;
        }
        
        for ( int i = 0; i < grid.length; i ++ ) {
            for ( int j = 0; j < grid[i].length; j ++ ) {
               // 如果是1, 并且没被访问过, 则是一个岛屿, 此时应该将这个1横向和纵向的所有1设置为true 
               if ( grid[i][j] == '1' && !isVisited[i][j] ) {
                   isVisited[i][j] = true;
                   searchIsland( i, j, grid );
                   count ++;
               }
            }
        }
        
        return count;
    }
    
    // 查找坐标[ outIndex, inIndex ]附近的所有1
    public void searchIsland (int outIndex, int inIndex, char[][] grid) {
        for ( int i = 0; i < 4; i ++ ) {
            int newOutIndex = outIndex + data[i][0];
            int newInIndex = inIndex + data[i][1];
            
            if ( newOutIndex < grid.length && newOutIndex >= 0 && 
                 newInIndex < grid[0].length && newInIndex >= 0 &&
                 grid[newOutIndex][newInIndex] == '1' && 
                 !isVisited[newOutIndex][newInIndex] ) {
                isVisited[newOutIndex][newInIndex] = true;
                searchIsland( newOutIndex, newInIndex, grid );
            }
        }
    }
}
