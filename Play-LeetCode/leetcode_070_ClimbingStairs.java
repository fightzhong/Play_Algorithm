import java.util.HashMap;

/**
 * 利用记忆化搜索, 当一个值已经求得后把它放在visited中, 下次不再去求这个值, 直接取
 */
public class leetcode_070_ClimbingStairs {
    HashMap<Integer, Integer> visited = new HashMap<>();

    public int climbStairs(int n) {
        if ( n == 1 ) {
            return 1;
        }
        
        if ( n == 2 ) {
            return 2;
        }
        
        int result1 = visited.containsKey( n - 1 ) ? visited.get( n - 1 ) : climbStairs( n - 1 );
        int result2 = visited.containsKey( n - 2 ) ? visited.get( n - 2 ) : climbStairs( n - 2 );
        
        visited.put( n, result1 + result2 );
        
        return result1 + result2;
    }
    
    public static void main(String[] args) {
        System.out.println( new leetcode_070_ClimbingStairs().climbStairs(2) );
    }
}
