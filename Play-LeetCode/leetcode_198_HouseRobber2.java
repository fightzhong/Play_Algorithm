import java.util.Arrays;

public class leetcode_198_HouseRobber2 {
    public static void main(String[] args) {
        System.out.println( new leetcode_198_HouseRobber2().rob( new int[] {1,2,3,1} ) );
    }
    
    private int[] robMoney;
    public int rob(int[] nums) {
        if ( nums.length == 0 ) {
            return 0;
        }
        
        if ( nums.length == 1 ) {
            return nums[0];
        }
        
        robMoney = new int[nums.length];
        Arrays.fill( robMoney, 0 );

        for ( int i = 0; i < nums.length; i++ ) {
            // 当前正在偷第i家, 等于前i - 2或者i - 3家的钱加上当前这一家的钱
            int m1 = i - 2 >= 0 ? nums[i] + robMoney[i - 2] : nums[i];
            int m2  = i - 3 >= 0 ? nums[i] + robMoney[i - 3] : nums[i];
            robMoney[i] = Math.max( m1, m2 );
        }

        
        return Math.max( robMoney[nums.length - 1], robMoney[nums.length - 2] ) ;
    }
}
