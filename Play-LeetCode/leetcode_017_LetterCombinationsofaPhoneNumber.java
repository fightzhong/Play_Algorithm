import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  跟第二种方式解决方式是一样的
 *  <1> 首先我们需要创建一个映射, 用于表示每个数字可以得到哪些字符
 *  <2> 要想得到这些数字的组合, 那么数字有多少个, 我们就要进行多少次的拼接, 
 *      创建一个ArrayList<String> list, 将第一个数字代表的字符放进去, 然后继续遍历
 *      字符串中的其它数字, 然后每遍历一个数字, 就将该数字对应的字符与原来的list进行拼接
 */
public class leetcode_017_LetterCombinationsofaPhoneNumber {
    public static void main(String[] args) {
        System.out.println(new leetcode_017_LetterCombinationsofaPhoneNumber().letterCombinations("23"));
    }
    
    public List<String> letterCombinations(String digits) {
        HashMap<Character, ArrayList<String>> map = getMap();
        if ( digits.length() == 1 ) {
            return map.get( digits.charAt(0) );
        } else if ( digits.length() == 0 ) {
            return map.get( '0' );
        }
        
        ArrayList<String> curList = map.get( digits.charAt(0) );
        List<String> laterList = letterCombinations( digits.substring(1) );
        
        ArrayList<String> result = new ArrayList<String>();
        for ( String s: curList ) {
           for ( String s2: laterList ) {
               result.add( s + s2 );
           } 
        }
        
        return result;
    }
    
    public HashMap<Character, ArrayList<String>> getMap () {
        HashMap<Character, ArrayList<String>> map =  new HashMap<>();
        char curChar = 97;
        int num = 3;
        for ( int i = 50; i <= 57; i ++ ) {
            ArrayList<String> arr = new ArrayList<String>();
            if ( i == 55 || i == 57 ) {
                num = 4;
            } else {
                num = 3;
            }
            
            for ( int j = 0; j < num; j ++ ) {
                arr.add( curChar++ + "" );
            }
            
            map.put( (char)i, arr );
        }
        
        map.put( '0', new ArrayList<String>() );
        return map;
    }
}
