import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 思路: 遍历每一个元素, 对每一个元素都去查找路径
 *
 */
public class leetcode_417_PacificAtlanticWaterFlow {
    private boolean[][] isVisited;
    private int[][] flowData = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    
    public static void main(String[] args) {
        int[][] matrix = new int[][] { {1,2,3}, {8,9,4}, {7,6,5} };
        new leetcode_417_PacificAtlanticWaterFlow().pacificAtlantic( matrix );
    }
    
    public List<int[]> pacificAtlantic(int[][] matrix) {
        ArrayList<int[]> list = new ArrayList<>();
        if ( matrix.length == 0 ) {
            return list;
        }
        
        isVisited = new boolean[matrix.length][matrix[0].length];
        for ( boolean[] b: isVisited ) {
            Arrays.fill( b, false );
        }
        
        // 对每一个元素都进行遍历, 查看是否符合条件
        for ( int i = 0; i < matrix.length; i ++ ) {
            for ( int j = 0; j < matrix[i].length; j ++ ) {
                isVisited[i][j] = true;
                boolean[] hasPath = new boolean[] { false, false };
                searchPath( i, j, matrix, hasPath );
                isVisited[i][j] = false;

                if ( hasPath[0] && hasPath[1] ) {
                    list.add( new int[] { i, j } );
                    
                    // 同一数组下, 如果一个值能够满足条件, 则其后面相连的所有比其大的值都能满足条件
                    while ( j + 1 < matrix[0].length && matrix[i][j + 1] >= matrix[i][j] ){
                        list.add( new int[] { i, j + 1 } );
                        j++;
                    }
                }
            }
        }
        
        return list;
    }
    
    // 判断一个坐标是否能流向太平洋
    private void searchPath (int outIndex, int inIndex, int[][] matrix, boolean[] hasPath) {
        for ( int i = 0; i < 4; i ++ ) {
            int newOutIndex = outIndex + flowData[i][0];
            int newInIndex = inIndex + flowData[i][1];
            
            // 能到达太平洋
            if ( newOutIndex < 0 || newInIndex < 0 ) {
                hasPath[0] = true;
            }
            
            // 能到达大西洋
            if ( newOutIndex >= matrix.length || newInIndex >= matrix[0].length ) {
                hasPath[1] = true;
            }
            
            // 如果没越界, 判断与当前值的大小, 小于等于, 则需要递归下去查找下一层
            // 如果hasPath[0] 与 hasPath[1]都为true了, 就不用再找下去了
            if ( newOutIndex < matrix.length && newOutIndex >= 0 &&
                 newInIndex < matrix[0].length && newInIndex >= 0 &&
                 matrix[newOutIndex][newInIndex] <= matrix[outIndex][inIndex] &&
                 !isVisited[newOutIndex][newInIndex] &&
                 ( !hasPath[0] || !hasPath[1] ) ) {
                
                isVisited[newOutIndex][newInIndex] = true;
                searchPath( newOutIndex, newInIndex, matrix, hasPath );
                isVisited[newOutIndex][newInIndex] = false;
                
            }
        }
    }
}
