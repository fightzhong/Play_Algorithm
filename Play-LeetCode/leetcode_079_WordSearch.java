import java.util.ArrayList;
import java.util.HashMap;

/**
 * 思路: 
 *  首先找到字符串的第一个字符在整个二维平面出现的位置, 然后对这些位置开始进行上下左右递归查找, 并且维护一个数组boolean[] b = new boolean[]{false}
 *  一旦找到了满足条件的路径, 就将该数组的false改成true, 并且!!!!!!!一定要终止查找!!!!!否则会出现超时的Time Limit Exceeded, 查找过程中, 通过map映射
 *  来控制一个坐标是否被访问过!!
 *  
 *  下一个解法是对该方法的优化!!!!这个解法可以不看!!过于繁琐
 */ 
public class leetcode_079_WordSearch {
    public static void main(String[] args) {
        char[][] board = new char[][] { {'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'} };
        String word = "ABFDECCESr "; // "ABCCED" "SEE"  "ABCB"
        System.out.println( new leetcode_079_WordSearch().exist( board, word ) );
    }
    
    public boolean exist (char[][] board, String word) {
        if ( word.length() == 0 ) {
            return false;
        }
        
        ArrayList<Element> list = getAllPosition( board, word );
        for ( Element e: list ) {
            HashMap<Element, Boolean> map = new HashMap<Element, Boolean>();
            map.put( e, true );
            boolean[] b = new boolean[] { false };
            getTrail( map, b, e,  1, board, word );
            map.remove( e );
            if ( b[0] ) {
                return true;
            }
        }
        
        return false;
    }
    
    // 对每一个元素都进行递归, 并传入一个HashMap来判断是否已经考虑过
    public void  getTrail (HashMap<Element, Boolean> map , boolean[] b, Element ele, int index,char[][] board,  String word) {
        if ( index == word.length() ) {
            b[0] = true;
            return;
        }
        
        // 目标元素
        char target = word.charAt( index );
        
        // 获取元素ele的上方是否存在目标元素
        Element top =  getTop( ele, target, board );
        // 存在, 则将该元素放入已经访问的名单
        if ( top != null && !map.containsKey( top ) && b[0] == false ) {
            map.put( top, true );
            getTrail( map, b, top, index + 1, board, word );
            map.remove( top );
        }
        
        // 获取元素ele的下方是否存在目标元素
        Element bottom =  getBottom( ele, target, board );
        if ( bottom != null && !map.containsKey( bottom ) && b[0] == false ) {
            map.put( bottom, true );
            getTrail( map, b, bottom, index + 1, board, word );
            map.remove( bottom );
        }
        
        // 获取元素ele的左方是否存在目标元素
        Element left =  getLeft( ele, target, board );
        if ( left != null && !map.containsKey( left ) && b[0] == false ) {
            map.put( left, true );
            getTrail( map, b, left, index + 1, board, word );
            map.remove( left );
        }
        
        
        // 获取元素ele的右方是否存在目标元素
        Element right =  getRight( ele, target, board );
        if ( right != null && !map.containsKey( right ) && b[0] == false ) {
            map.put( right, true );
            getTrail( map, b, right, index + 1, board, word );
            map.remove( right );
        }
        
    }
    
    public Element getRight (Element ele, char target, char[][] board) {
        int outIndex = ele.getOutIndex();
        int inIndex = ele.getInIndex();
        
        // 右边不存在的情况
        if ( inIndex + 1 >= board[outIndex].length ) {
            return null;
        }
        
        if ( board[outIndex][inIndex + 1] == target ) {
            return new Element( target, outIndex, inIndex + 1 );
        }
        
        return null;
    }
    
    public Element getLeft (Element ele, char target, char[][] board) {
        int outIndex = ele.getOutIndex();
        int inIndex = ele.getInIndex();
        
        // 左边不存在的情况
        if ( inIndex - 1 < 0 ) {
            return null;
        }
        
        if ( board[outIndex][inIndex - 1] == target ) {
            return new Element( target, outIndex, inIndex - 1 );
        }
        
        return null;
    }
    
    public Element getTop (Element ele, char target, char[][] board) {
        int outIndex = ele.getOutIndex();
        int inIndex = ele.getInIndex();
        
        // 上一层不存在的情况
        if ( outIndex - 1 < 0 || inIndex >= board[ outIndex - 1 ].length) {
            return null;
        }
        
        // 存在的情况
        if ( board[ outIndex - 1 ][ inIndex ] == target ) {
            return new Element( target, outIndex - 1, inIndex );
        }
        
        return null;
    }
    
    public Element getBottom (Element ele, char target, char[][] board) {
        int outIndex = ele.getOutIndex();
        int inIndex = ele.getInIndex();
        
        // 下一层不存在的情况
        if ( outIndex + 1 >= board.length || inIndex >= board[ outIndex + 1 ].length) {
            return null;
        }
        
        // 存在的情况
        if ( board[ outIndex + 1 ][ inIndex ] == target ) {
            return new Element( target, outIndex + 1, inIndex );
        }
        
        return null;
    }
    
    // 获取字符串中第0号索引的字符出现的所有位置
    public ArrayList<Element> getAllPosition (char[][] board, String word) {
        ArrayList<Element> list = new ArrayList<Element>();
        char target = word.charAt( 0 );
        for ( int i = 0; i < board.length; i ++ ) {
            for ( int j = 0; j < board[i].length; j ++ ) {
                if ( target == board[i][j] ) {
                    list.add( new Element( target, i, j ) );
                }
            }
        }
        
        return list;
    }
    
    class Element {
        char c;
        int outIndex;
        int inIndex;
        
        public Element(char c, int outIndex, int inIndex) {
            super();
            this.c = c;
            this.outIndex = outIndex;
            this.inIndex = inIndex;
        }
        
        public char getC() {
            return c;
        }

        public int getOutIndex() {
            return outIndex;
        }

        public int getInIndex() {
            return inIndex;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + c;
            result = prime * result + inIndex;
            result = prime * result + outIndex;
            return result;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Element other = (Element) obj;
            if (c != other.c)
                return false;
            if (inIndex != other.inIndex)
                return false;
            if (outIndex != other.outIndex)
                return false;
            return true;
        }
        
        @Override
        public String toString() {
            return "Element [c=" + c + ", outIndex=" + outIndex + ", inIndex=" + inIndex + "]";
        }
    }
}
