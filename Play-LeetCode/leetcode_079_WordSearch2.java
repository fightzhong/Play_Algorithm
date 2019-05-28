import java.util.ArrayList;
import java.util.Arrays;
/**
 * 思路: 
 * 跟第一种解法的思路一模一样, 不同的是本次解法对一个元素是否被访问采用了一个二维数组来进行控制, 而不是map映射
 * 其次, 获取上下左右也通过data数组来控制, 而不是通过四个函数!!!
 */ 
public class leetcode_079_WordSearch2 {
    public static void main(String[] args) {
        char[][] board = new char[][] { {'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'} };
        String word = "SEE"; // "ABCCED" "SEE"  "ABCB"
        System.out.println( new leetcode_079_WordSearch2().exist( board, word ) );
    }
    
    private boolean[][] isVisited;
    private int[][] data = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    
    public boolean exist (char[][] board, String word) {
        // 初始化isVisited数组
        isVisited = new boolean[board.length][board[0].length];
        for ( boolean[] b: isVisited ) {
            Arrays.fill( b, false );
        }
        
        // 获取字符串的第一个字符出现的所有位置
        ArrayList<int[]> firstWord = getFirstCharOfWord( board, word );
        if ( firstWord.size() == 0 ) {
            return false;
        }
        
        for ( int[] arr: firstWord ) {
            isVisited[arr[0]][arr[1]] = true;
            
            boolean[] result = new boolean[] { false };
            search( word, 1, arr[0], arr[1], board, result );
            if ( result[0] ) {
                return true;
            }
            
            isVisited[arr[0]][arr[1]] = false;
        }
        
        return false;
    }
    
    // 依次查找字符串当前字符的上下左右是否存在满足条件的
    private void search (String word, int wordIndex, int outIndex, int inIndex, char[][] board, boolean[] result) {
        // 找到了最后的字符串了
        if ( wordIndex == word.length() ) {
            result[0] = true;
            return;
        }
        
        // outIndex, inIndex即当前字符, 需要查看当前字符的上下左右是否存在一个满足条件的
        for ( int i = 0; i < 4; i ++ ) {
            int newOutIndex = outIndex + data[i][0];
            int newInIndex = inIndex + data[i][1];
            
            // 不越界, 四周的元素存在等于字符串的下一个字符, 这个四周的元素没有被访问过
            // 满足上面的三个条件, 说明找到了满足条件的
            if ( newOutIndex < board.length && newOutIndex >= 0 &&
                 newInIndex < board[0].length && newInIndex >= 0 &&
                 board[newOutIndex][newInIndex] == word.charAt( wordIndex ) &&
                 isVisited[newOutIndex][newInIndex] == false &&
                 result[0] == false ) {
                
                isVisited[newOutIndex][newInIndex] = true;
                search( word, wordIndex + 1, newOutIndex, newInIndex, board, result );
                isVisited[newOutIndex][newInIndex] = false;
            }
        }
    }
    
    // 获取字符串的第一个字符出现在board的所有位置
    private ArrayList<int[]> getFirstCharOfWord (char[][] board, String word){
        char c = word.charAt( 0 );
        ArrayList<int[]> list = new ArrayList<int[]>();
        for ( int i = 0; i < board.length; i ++ ) {
            for ( int j = 0; j < board[i].length; j ++ ) {
                if ( board[i][j] == c ) {
                    list.add( new int[] { i, j } );
                }
            }
        }
        return list;
    }
}
