// 利用动态规划
public class leetcode_091_DecodeWays2 {
    public static void main(String[] args) {
        System.out.println( new leetcode_091_DecodeWays2().numDecodings("110") );
    }
    
    public int numDecodings(String s) {
        // 处理以0开头的
        if ( s.charAt( 0 ) == '0' ) {
            return 0;
        }

        int[] memo = new int[s.length()];
        memo[0] = 1;

        for ( int i = 1; i < s.length(); i ++ ) {
            // 获取当前字符和前一个字符即两个字符构成的数字
            int num = Integer.parseInt( s.substring( i - 1, i + 1 ) );
            int count = 0;  // 对于当前字符来说从[0, i]位置的字符串解码的个数

            // 这里需要分两步判断, 当前字符是0和不是0
            if ( s.charAt( i ) == '0' ) {
                // 如果当前字符是0, 并且在10-26之间, 说明是10或者20
                // 那么count应该是前i - 2个字符对应的值, 因为前n-1个字符即使能够有值, 但是当前字符0是不能被解码的
                if ( num >= 10 && num <= 26 ) {
                    count = i - 2 >= 0 ? memo[i - 2] : 1;
                } else {
                    // 如果到了这一步, 即num < 10, 并且当前字符为0, 那么就一定是00, 应该直接返回0
                    return 0;
                }
            } else {
                // 当前字符不是0, 那么就需要判断两个字符构成的数字num是否在可解码的范围内了
                if ( num >= 10 && num <= 26 ) {
                    count = i - 2 >= 0 ? memo[i - 1] + memo[i - 2] : 2;
                } else {
                    count = memo[i - 1];
                }
            }

            memo[i] = count;
        }

        return memo[s.length() - 1];
    }
}
