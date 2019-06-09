import java.util.Arrays;

public class leetcode_198_HouseRobber {
    private int[] robMoney;
    public int rob(int[] nums) {
        if ( nums.length == 0 ) {
            return 0;
        }
        
        robMoney = new int[nums.length];
        Arrays.fill( robMoney, -1 );
        // 从第一家开始偷
        int m1 = robHouse( nums, 0 );
        // 从第二家开始偷
        int m2 = 0;
        if ( nums.length >= 2 ) {
            m2 = robHouse( nums, 1 );
        }
        
        return Math.max( m1, m2 );
    }
    
    // 从第index个房子开始偷, 获取以index开始能偷到的最大的钱数
    public int robHouse (int[] nums, int index) {
        if ( index == nums.length - 1 || index == nums.length - 2 ) {
            return nums[index];
        }
        
        // 偷的情况只有两种, 第一家会触发警报, 第二家和第三家才可以, 不能先选第四家, 否则等于漏掉了两家
        int m1 = 0;
        int m2 = 0;
        if ( index + 2 < nums.length ) {
            m1 = robMoney[index + 2] == -1 ? robHouse( nums, index + 2 ) : robMoney[index + 2];
        }
        if ( index + 3 < nums.length ) {
            m2 = robMoney[index + 3] == -1 ? robHouse( nums, index + 3 ) : robMoney[index + 3];
        }
        
        robMoney[index] = nums[index] + Math.max( m1, m2 );
        
        return robMoney[index];
    }
}
