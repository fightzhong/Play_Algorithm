import java.util.Arrays;
/**
 * 思路:
 *  对整个二维平面进行遍历, 遍历过程中找到边缘的O, 以边缘的O开始执行递归算法searcAllO, 找到与该边缘O能连接起来的所有O
 *  并将这些O设置为false, 遍历结束后, 对所有为true的元素设置成字符X
 *
 */
public class leetcode_130_SurroundedRegions {
    private boolean[][] isVisited;
    int[][] data = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public void solve(char[][] board) {
        if ( board.length == 0 ) {
            return;
        }
        
        isVisited = new boolean[board.length][board[0].length];
        for ( boolean[] b: isVisited ) {
            Arrays.fill( b, false );
        }
        
        for ( int i = 0; i < board.length; i ++ ) {
            for ( int j = 0; j < board[0].length; j ++ ) {
                char c = board[i][j];
                if ( c == 'O' && !isVisited[i][j] && isEdge( i, j, board ) ) {
                    searchAllO( i, j, board );
                }
            }
        }
        
        for ( int i = 0; i < isVisited.length; i ++ ) {
            for ( int j = 0; j < isVisited[i].length; j ++ ) {
                // 如果是false, 则这个值要么是X, 要么是被包裹的O
                if ( !isVisited[i][j] ) {
                    board[i][j] = 'X';
                }
            }   
        }
    }
    
    // 判断这个字符O是否是边缘的O
    private boolean isEdge (int outIndex, int inIndex, char[][] board) {
        for ( int i = 0; i < 4; i ++ ) {
            int newOutIndex = outIndex + data[i][0];
            int newInIndex = inIndex + data[i][1];
            
            if ( newOutIndex >= board.length || newOutIndex < 0 || 
                    newInIndex >= board[0].length || newInIndex < 0 ) {
                return true;
            }
        }
        
        return false;
    }
    
    // 找到与坐标[ outIndex, inIndex ]相连的所有值为O的, 并设置为已经访问
    private void searchAllO (int outIndex, int inIndex, char[][] board) {
        isVisited[outIndex][inIndex] = true;
        for ( int i = 0; i < 4; i ++ ) {
            int newOutIndex = outIndex + data[i][0];
            int newInIndex = inIndex + data[i][1];
            
            if ( newOutIndex >= 0 && newOutIndex < board.length &&
                 newInIndex >= 0 && newInIndex < board[0].length && 
                 board[newOutIndex][newInIndex] == 'O' &&
                 !isVisited[newOutIndex][newInIndex] ) {
                searchAllO( newOutIndex, newInIndex, board );
            }
        }
    }
}
