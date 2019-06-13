/**
 *  .动态规划: 
 *          第一步: 找状态, 求左上角到右下角有几种不同的路径
 *          第二步: 找状态转移方程, 以f(x,y)代表一个点到右下角的路径个数, 则f(0,0)代表从左上角到右下角的路径个数
 *                      方程: f(x,y) = f(x, y + 1) + f(x + 1, y)   
 *                      一个点到右下角独一无二的路径个数   等于  其右边的点到右下角的路径个数   加上   其下边的点到右下角的路径个数
 */
public class leetcode_062_UniquePaths3 {
    public static void main(String[] args) {
        System.out.println( new leetcode_062_UniquePaths3().uniquePaths(3, 2) );
    }
    
    public int uniquePaths(int m, int n) {
        int[][] paths = new int[m][n];
        paths[m - 1][n - 1] = 1;
        for ( int i = m - 1; i >= 0; i -- ) {
            for ( int j = n - 1; j >= 0; j -- ) {
                if ( i == m - 1 && j == n - 1 ) continue; // 最后一个不能去计算
                int p1 = j + 1 < n ? paths[i][j + 1] : 0; // 获取右边的点到右下角的路径个数, 如果不存在这个点, 那么个数就为0
                int p2 = i + 1 < m ? paths[i + 1][j] : 0; // 获取下边的点到右下角的路径个数, 如果不存在这个点, 那么个数就为0
                paths[i][j] = p1 + p2; // 状态转移方程的代码实现
            }
        }
        
        return paths[0][0];        
    }
}
