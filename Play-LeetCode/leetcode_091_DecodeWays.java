import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class leetcode_091_DecodeWays {
    public static void main(String[] args) {
        System.out.println( new leetcode_091_DecodeWays().numDecodings("4321") );
    }
    
    HashMap<List<Integer>, Integer> map = new HashMap<>();
    public int numDecodings(String s) {
        return decodeCount( s, 0 );
    }
    
    // 对字符串以startIndex开始到结尾的字符进行解码, 返回解码后的个数
    public int decodeCount (String s, int startIndex) {
        if ( startIndex == s.length() || startIndex == s.length() + 1 ) {
            return 1;
        }
        
        // 一次可以选择一个字符, 也可以选择两个字符
        int counts = 0;
        for ( int i = 1; i <= 2; i ++ ) {
            int endIndex = startIndex + i;
            List<Integer> range = Arrays.asList( startIndex, endIndex );
            if ( map.containsKey( range ) ) {
                counts += map.get( range );
            } else if ( endIndex <= s.length() ) {
                String str = s.substring( startIndex, endIndex );
                int num = Integer.parseInt( str );
                if ( num > 0 && num <= 26 && str.charAt( 0 ) != '0' ) {
                    int count = decodeCount( s, endIndex );
                    counts += count;
                    map.put( range, count );
                }
                
            }
        }
        
        return counts;
    }
}
