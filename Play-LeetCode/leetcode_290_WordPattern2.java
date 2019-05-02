import java.util.HashMap;

/**
     Description:
         Given a pattern and a string str, find if str follows the same pattern.
         Here follow means a full match, such that there is a bijection between 
         a letter in pattern and a non-empty word in str.
     
     Example 1:
        Input: pattern = "abba", str = "dog cat cat dog"
        Output: true
        
    Example 2:
        Input:pattern = "abba", str = "dog cat cat fish"
        Output: false
 
 
 */
public class leetcode_290_WordPattern2 {
    public static void main(String[] args) {
        String pattern = "abba";
        String str = "dog cat cat dog";
//        String pattern = "abc";
//        String str = "b c a";
//        String pattern = "beef";
//        String str = "";
        
        System.out.println(new leetcode_290_WordPattern2().wordPattern(pattern, str));
    }
    
    public boolean wordPattern(String pattern, String str) {
        
        String[] strArr = str.split(" ");
        
        if (pattern.length() != strArr.length) {
            return false;
        }
        
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        
        
        for (Integer i = 0; i < strArr.length; i++) {
            if (map1.put(strArr[i], i) != map2.put(pattern.charAt(i), i)) {
                return false;
            }
        }
        
        return true;
    }
}
