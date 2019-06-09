import java.util.Arrays;

/**
 * 类似于198题的偷钱HouseRobber, 不同的是这个题出现了一个环状, 那么可以进行两次偷取, 一次偷取第一家不投最后一家
 * 第二次偷取最后一家不偷取第一家, 即偷取的开始和结尾进行了一下设置
 */
public class leetcode_213_HouseRobberII {
    public int rob(int[] nums) {
        if ( nums.length == 0 ) {
            return 0;
        }
        
        if ( nums.length == 1 ) {
            return nums[0];
        }
        
        int[] money = new int[nums.length];
        Arrays.fill( money, -1 );
        
        // 第一次:抢第一家， 不抢最后一家 
        robMoney( 0, nums.length - 1, nums, money );
        robMoney( 1, nums.length, nums, money );
        int max1 = Math.max( money[0], money[1] );
        
        // 第二次:不抢第一家， 抢最后一家 
        Arrays.fill( money, -1 );
        robMoney( 1, nums.length, nums, money );
        if ( nums.length > 2 ) {
            robMoney( 2, nums.length, nums, money );
        }
        // 如果只有三个元素，则就只返回第二个就好了, 不用去判断第三个
        int max2 = nums.length > 2 ? Math.max( money[1], money[2] ) : money[1];
        
        return Math.max( max1, max2 );
    }
    
    public int robMoney (int index, int end, int[] nums, int[] money) {
        int m1 = 0;
        int m2 = 0;
        if ( index + 2 < end ) {
            m1 = money[index + 2] == -1 ? robMoney( index + 2, end, nums, money ) : money[index + 2];
        }
        
        if ( index + 3 < end ) {
            m2 = money[index + 3] == -1 ? robMoney( index + 3, end, nums, money ) : money[index + 3];
        }
        
        money[index] = nums[index] + Math.max( m1, m2 );
        return money[index];
    }
}
