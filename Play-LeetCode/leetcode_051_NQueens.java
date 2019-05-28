import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * N皇后问题: 
 *      两个皇后不能在同一水平线, 同一竖直线的相邻位置, 以及同一对角线下(分为45度对角线和135度对角线), 才能达到不互相攻击
 *      遍历n次, 放置第一个皇后的位置为n个位置, 对每一个位置都去计算下一个皇后的位置 
 */
// 如果list的大小返回空就不用继续计算下去了
public class leetcode_051_NQueens {
    private int n; // 存储一个n的副本, 用来方便其它函数使用这个值
    private boolean[] isVisited; // 列的出现情况
    private boolean[][] diagonalIsVisited; // 对角线的出现情况
    
    public List<List<String>> solveNQueens(int n) {
        // 初始化信息
        this.n = n;
        this.isVisited = new boolean[n];
        Arrays.fill( isVisited, false );
        diagonalIsVisited = new boolean[n][n];
        for ( boolean[] b: diagonalIsVisited ) {
            Arrays.fill( b, false );
        }
        
        List<List<String>> allList = new ArrayList<List<String>>();
        // 第一个皇后可以出现在n个位置
        for ( int i = 0; i < n; i ++ ) {
            isVisited[i] = true;
            diagonalIsVisited[0][i] = true;
            List<List<String>> list = getAllSolutions( 0, i, 0 );
            if ( list != null ) {
                for ( List<String> l: list ) {
                    allList.add( l );
                }
            }
            isVisited[i] = false;
            diagonalIsVisited[0][i] = false;
        }
        
        return allList;
    }
    
    // 根据传进来的坐标, 判断下一个皇后出现的位置
    public List<List<String>> getAllSolutions (int outIndex, int inIndex, int count) {
        // 存储所有的情况
        List<List<String>> allList = new ArrayList<List<String>>();
        // 当前坐标的字符串表示
        String str = getStringOfCurrentArea( inIndex );

        // 递归终止条件: 递归到最后一个的时候
        if ( count == n - 1 ) {
            List<String> aList = new ArrayList<String>();
            aList.add( str );
            allList.add( aList );
            return allList;
        }
        
        List<int[]> nextLocation = getNextQueenLocation( outIndex, inIndex );   
        // 如果下一个皇后没有位置可以放置了, 那么就说明这个情况查找失败了, 此时选择返回allList(里面是没有值的)
        if ( nextLocation.size() == 0 ) {
            return null;
        }
        
        for ( int[] location: nextLocation ) {
            isVisited[location[1]] = true;
            diagonalIsVisited[location[0]][location[1]] = true;
            List<List<String>> list = getAllSolutions( location[0], location[1], count + 1 );
            isVisited[location[1]] = false;
            diagonalIsVisited[location[0]][location[1]] = false;
            
            if ( list != null ) {
                // 对这些进行添加
                for ( List<String> l: list ) {
                    l.add( str );
                    allList.add( l );
                }
            }
        }
        
        return allList.size() == 0 ? null : allList;
    }
    
    public String getStringOfCurrentArea (int index) {
        String str = "";
        for ( int i = 0; i < n; i ++ ) {
            if ( i == index ) {
                str += "Q";
            } else {
                str += ".";
            }
        }
        
        return str;
    }
   
    // 传进来一个坐标, 获取下一个皇后可以出现的位置, 返回一个List
    public List<int[]> getNextQueenLocation (int outIndex, int inIndex ) {
        List<int[]> list = new ArrayList<int[]>();
        int newOutIndex = outIndex + 1;
        
        int newInIndex = inIndex - 2;
        while ( newInIndex >= 0 && newOutIndex < n ) {
            // 没有使用过才行
            if ( !hasUsed( newOutIndex, newInIndex ) ) {
                list.add( new int[] { newOutIndex, newInIndex } );
            }
            newInIndex --;
        }
        
        newInIndex = inIndex + 2;
        // 根据坐标[outIndex, inIndex]往后计算可以出现的位置
        while ( newInIndex < n && newOutIndex < n ) {
            // 没有使用过才行
            if ( !hasUsed( newOutIndex, newInIndex ) ) {
                list.add( new int[] { newOutIndex, newInIndex } );
            }
            newInIndex ++;
        }
        
        return list;
    }
    
    // 判断一个坐标, 其是否在同一列, 同一对角线上没有出现过元素
    public boolean hasUsed (int outIndex, int inIndex) {
        // 同一列是否出现过
        boolean b1 = isVisited[inIndex];
        // 对角线是否出现过元素
        boolean b2 = false;
        
        // 45度对角线(只要判断其上层对角线是否有元素就可以了)
        int newOutIndex = outIndex - 1;
        int newInIndex = inIndex + 1;
        while ( newOutIndex >= 0 && newInIndex < n && !b2 ) {
            if ( diagonalIsVisited[newOutIndex--][newInIndex++] ) {
                b2 = true;
                break;
            }
        }
        
        // 135度对角线(只要判断其上层对角线是否有元素就可以了)
        newOutIndex = outIndex - 1;
        newInIndex = inIndex - 1;
        while ( newOutIndex >= 0 && newInIndex >= 0 && !b2 ) {
            if ( diagonalIsVisited[newOutIndex--][newInIndex--] ) {
                b2 = true;
                break;
            }
        }
        
        return b1 || b2;
    }
}
