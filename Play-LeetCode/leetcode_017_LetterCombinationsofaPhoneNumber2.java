import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  <1> 首先我们需要创建一个映射, 用于表示每个数字可以得到哪些字符
 *  <2> 要想得到这些数字的组合, 那么数字有多少个, 我们就要进行多少次的拼接, 
 *      创建一个ArrayList<String> list, 将第一个数字代表的字符放进去, 然后继续遍历
 *      字符串中的其它数字, 然后每遍历一个数字, 就将该数字对应的字符与原来的list进行拼接
 */
public class leetcode_017_LetterCombinationsofaPhoneNumber2 {
    public List<String> letterCombinations(String digits) {
        HashMap<Character, ArrayList<String>> map = getMap();
        return digits.length() == 0 ? new ArrayList<String>() : getCombination( digits, map );
    }
    
    public ArrayList<String> getCombination (String digits, HashMap<Character, ArrayList<String>> map) {
        
        ArrayList<String> result = map.get( digits.charAt(0) );
        for ( int i = 1; i < digits.length(); i ++ ) {
            ArrayList<String> curList = map.get( digits.charAt(i) );
            ArrayList<String> combination = new ArrayList<String>();
            for ( int j = 0; j < result.size(); j ++ ) {
                for ( int k = 0; k < curList.size(); k ++ ) {
                    combination.add( result.get(j) + curList.get(k) );
                }
            }
            
            result = combination;
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
        
        return map;
    }
}
