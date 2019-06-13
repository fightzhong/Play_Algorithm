// 利用动态规划
public class leetcode_091_DecodeWays2 {
    public static void main(String[] args) {
        System.out.println( new leetcode_091_DecodeWays2().numDecodings("110") );
    }
    
    public int numDecodings(String s) {
        if ( s.length() == 0 || s.charAt(0) == '0' ) {
            return 0;
        }
        
        if ( s.length() == 1 ) {
            return 1;
        }
        
        int[] memo = new int[s.length()];
        memo[0] = 1;
        // 第二位会出现四种情况: 满足条件的两位数如10/11( 如果第二个数为0, 则memo[1] = 1, 不为0, 则memo[1] = 2 )
        // 不满足条件的两位数30/33( 如果第二个数为0, 则memo[1] = 0, 不为0, 则memo[1] = 1 )
        if ( Integer.parseInt( s.substring( 0, 2 ) ) <= 26 ) {
            if ( s.charAt(1) == '0' ) {
                memo[1] = 1;
            } else {
                memo[1] = 2;
            }
        } else {
            if ( s.charAt(1) == '0' ) {
                memo[1] = 0;
            } else {
                memo[1] = 1;
            }
        }
        
        for ( int i = 2; i < s.length(); i ++ ) {
            int count = 0;
            // 当前字符独占一位
            char c = s.charAt( i );
            // 如果是合法字符, 那么这个字符能够成为前i - 1个字符切割的一个子字符
            if ( c != '0' ) {
                count = memo[ i - 1 ];
            }
            
            // 当前这个字符与前面的字符构成一个两位数
            String str = s.substring( i - 1, i + 1 );
            // 如果是合法的字符, 那么这个字符能够成为前i - 2个字符切割的一个子字符
            if ( Integer.parseInt( str ) >= 10 && Integer.parseInt( str ) <= 26 ) {
                count += memo[i - 2];
            }
            
            // 两个字符的时候有特殊情况, 如果这两个字符都是0的话, 则不能进行切割解码
            if ( str.equals( "00" ) || count == 0 ) {
                return 0;
            }
            
            memo[i] = count;
        }
        
        return memo[s.length() - 1];
    }
}
