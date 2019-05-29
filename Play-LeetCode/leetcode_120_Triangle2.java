import java.util.Arrays;
import java.util.List;

/**
 * 动态规划的思想: 自底向上的获取每一层每一个元素的最小值
 */
public class leetcode_120_Triangle2 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] sum = new int[triangle.size()][];
        for ( int i = 0; i < sum.length; i ++ ) {
            sum[i] = new int[ i + 1 ];
        }
        
        // 初始化最后一行的数据
        for ( int i = 0; i < sum[sum.length - 1].length; i ++ ) {
            sum[sum.length - 1][i] = triangle.get( sum.length - 1 ).get( i );
        }
        
        for ( int i = triangle.size() - 2; i >= 0; i -- ) {
            for ( int j = 0; j < sum[i].length; j ++ ) {
                sum[i][j] = triangle.get( i ).get( j ) + Math.min( sum[i + 1][j], sum[i + 1][j + 1] );
            }
        }
        
        return sum[0][0];
    }
}
