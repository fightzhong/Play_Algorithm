import java.util.ArrayList;
import java.util.HashMap;
/**
 * 思路:
 *  <1> 对整个board进行遍历一次, 遍历的过程中, 完善三个信息:
 *          一: 每一行中字符出现的情况 => private ArrayList<HashMap<Character, Boolean>> rowVisited;    
 *          二: 每一列中字符出现的情况 => private ArrayList<HashMap<Character, Boolean>> colVisited;    
 *          三: 每一个3*3的盒子中出现字符的情况  => ArrayList<HashMap<Character, Boolean>> subBoxVisited;      
 *          四: 所有的点字符的坐标 => ArrayList<int[]> allPoint
 *          
 *          对所有的点字符进行递归, 递归过程中对每一个字符通过getCharacter函数获取该点字符坐标下可以出现的字符, 
 *          然后维护三个visited数据, 一旦递归没成功, 则要恢复数据为false     
 */
public class leetcode_037_SudokuSolver {
    public static void main(String[] args) {
        char[][] board = new char[][] {
            {'5','3','.','.','7','.','.','.','.'},
             {'6','.','.','1','9','5','.','.','.'},
             {'.','9','8','.','.','.','.','6','.'},
             {'8','.','.','.','6','.','.','.','3'},
             {'4','.','.','8','.','3','.','.','1'},
             {'7','.','.','.','2','.','.','.','6'},
             {'.','6','.','.','.','.','2','8','.'},
             {'.','.','.','4','1','9','.','.','5'},
             {'.','.','.','.','8','.','.','7','9'}
        };
        new leetcode_037_SudokuSolver().solveSudoku( board );
    }
    
    private ArrayList<HashMap<Character, Boolean>> rowVisited;      // 行出现的情况
    private ArrayList<HashMap<Character, Boolean>> colVisited;      // 列出现的情况
    private ArrayList<HashMap<Character, Boolean>> subBoxVisited;   // 每一个3*3的盒子中出现字符的情况
    private final char[] allChar = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    private char[][] board;                                         // board的副本, 用于多个函数的使用
    private ArrayList<int[]> allPoint;                              // 所有的点字符
    
    public void solveSudoku(char[][] board) {
        this.board = board;
        // 初始化行, 列, 盒子中字符出现的情况
        init();
        
        // 执行填充字符的操作
        fillCharacter(0);
    }
    
    // 填充操作
    public boolean fillCharacter (int count) {
        // 如果count == allPoint.size(): 说明找到了答案: 此时应该将所有的点更换为指定的字符
        if ( count == allPoint.size() ) {
            return true;
        }
        
        // 获取第count个点字符的坐标
        int[] coordinate =  allPoint.get( count );
        // 获取该点字符所在的坐标可以出现的字符
        ArrayList<Character> chars = getCharacter( coordinate[0], coordinate[1] ); // 为空呢??????????????????????????????????
        for ( char c: chars ) {
            // 当前点字符被c取代, 那么就需要维护三个visited的值
            rowVisited.get( coordinate[0] ).put( c, true );
            colVisited.get( coordinate[1] ).put( c, true );
            subBoxVisited.get( coordinate[0] / 3 * 3 + coordinate[1] / 3 ).put( c, true );
            boolean b = fillCharacter(count + 1);
            // 如果b是true, 那么说明更改的方向是对的, 此时更改点字符为c字符
            if ( b ) {
                board[coordinate[0]][coordinate[1]] = c;
                return true;
            }
            
            rowVisited.get( coordinate[0] ).put( c, false );
            colVisited.get( coordinate[1] ).put( c, false );
            subBoxVisited.get( coordinate[0] / 3 * 3 + coordinate[1] / 3 ).put( c, false );
        }
        
        return false;
    }
    
    // 根据一个坐标, 获取这个坐标下可以出现的字符
    public ArrayList<Character> getCharacter (int outIndex, int inIndex){
        ArrayList<Character> chars = new ArrayList<>();
        for ( char c: allChar ) {
            // 只有三者都不包含的情况下才能作为坐标下的其中一个字符, 即三者都为false才说明三个都不包含
            if ( !rowVisited.get( outIndex ).get( c ) &&
                 !colVisited.get( inIndex ).get( c ) &&
                 !subBoxVisited.get( outIndex / 3 * 3 + inIndex / 3 ).get( c ) ) {
                chars.add( c );
            }
        }
        
        return chars;
    }
    
    // 初始化三个visited数据
    public void init () {
        allPoint = new ArrayList<int[]>();
        rowVisited = new ArrayList<HashMap<Character,Boolean>>();
        colVisited = new ArrayList<HashMap<Character,Boolean>>();
        subBoxVisited = new ArrayList<HashMap<Character,Boolean>>();
        for ( int i = 0; i < 9; i ++ ) {
            rowVisited.add( i, new HashMap<Character, Boolean>() );
            colVisited.add( i, new HashMap<Character, Boolean>() );
            subBoxVisited.add( i, new HashMap<Character, Boolean>() );
        }
        for ( int j = 0; j < 9; j ++ ) {
            for ( int i = 0; i < 9; i ++ ) {
                rowVisited.get(j).put( allChar[i], false );
                colVisited.get(j).put( allChar[i], false );
                subBoxVisited.get(j).put( allChar[i], false );
            }
        }
        
        for ( int i = 0; i < board.length; i ++ ) {
            for ( int j = 0; j < board[i].length; j ++ ) {
                if ( board[i][j] != '.' ) {
                    rowVisited.get( i ).put( board[i][j], true );
                    colVisited.get( j ).put( board[i][j], true );
                    // 获取第x个3*3的盒子: i / 3 * 3 + j / 3
                    subBoxVisited.get( i / 3 * 3 + j / 3 ).put( board[i][j], true );
                    
                } else {
                    allPoint.add( new int[] { i, j } );
                }
            }
        }
    }
}
