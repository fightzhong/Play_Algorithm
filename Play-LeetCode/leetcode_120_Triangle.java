import java.util.Arrays;
import java.util.List;

/**
 * 利用记忆话搜索, 上一层应该加上下一层相邻两个元素以下的三角形的最小值, 但是会出现同一个元素被递归两次的情况
 * 所以需要利用记忆化搜索, 对于已经算出了最小值的元素不再递归, 所以需要创建一个跟triangle一样的数组, 数组里面存放
 * Integer.MAX_VALUE, 每次对该元素递归前, 都去考虑是否已经计算过
 */
public class leetcode_120_Triangle {
    int[][] visited;
    public int minimumTotal(List<List<Integer>> triangle) {
        visited = new int[triangle.size()][];
        for ( int i = 0; i < visited.length; i ++ ) {
            visited[i] = new int[ i + 1 ];
            // 初始化为最大值
            Arrays.fill( visited[i], Integer.MAX_VALUE );
        }
        
        return getMinimum( triangle, 0, 0 );
    }
    
    public int getMinimum (List<List<Integer>> triangle, int row, int index) {
        if ( row == triangle.size() - 1 ) {
            return triangle.get( row ).get( index );
        }
        
        int result1;
        int result2;
        if ( visited[row + 1][index + 1] < Integer.MAX_VALUE ) {
            result1 = visited[row + 1][index + 1];
        } else {
            visited[row + 1][index + 1] = getMinimum( triangle, row + 1, index + 1 );
            result1 = visited[row + 1][index + 1];
        }
        
        if ( visited[row + 1][index] < Integer.MAX_VALUE ) {
            result2 = visited[row + 1][index];
        } else {
            visited[row + 1][index] = getMinimum( triangle, row + 1, index );
            result2 = visited[row + 1][index];
        }
        
        return triangle.get( row ).get( index ) + Math.min( result1, result2 );
    }
}
