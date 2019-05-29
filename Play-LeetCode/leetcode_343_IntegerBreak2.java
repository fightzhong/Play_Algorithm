import java.util.HashMap;

/**
 * 递归记忆化搜索:
 *   一个数的拆分为多少个数, 求这些数的乘积的最大值, 那么我们可以将一个数拆分为两个数, 求这两个数拆分后乘积的最大值
 *   拆分的两个数, 需要考虑这两个数拆分后的数是否比没拆分的大, 如果拆分后的数比当前值大才采用拆分后的数
 */
public class leetcode_343_IntegerBreak2 {
    public static void main(String[] args) {
     System.out.println( new leetcode_343_IntegerBreak2().integerBreak( 4 ) );   
    }
    
    private HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    {
        map.put( 1, 1 );
        map.put( 2, 1 );
    }
    
    public int integerBreak(int n) {
        if ( n == 1 || n == 2 ) {
            return 1;
        }
        
        // 拆分为两个数, 拆到一半就拆分结束了
        int max = 0;
        for ( int i = 1; i <= n / 2; i ++ ) {
            int n1 = map.containsKey( i ) ? map.get( i ) > i ? map.get( i ) : i : integerBreak( i );
            int n2 = map.containsKey( n - i ) ? map.get( n - i ) > ( n - i ) ? map.get( n - i ) : ( n - i ) : integerBreak( n - i );
            if ( n1 * n2 > max ) {
                max = n1 * n2;
            }
        }
        
        map.put( n, max );
        return max;
    }
}
