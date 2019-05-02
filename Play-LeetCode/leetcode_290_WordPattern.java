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
public class leetcode_290_WordPattern {
    public static void main(String[] args) {
        String pattern = "abba";
        String str = "dog cat cat fish";
//        String pattern = "beef";
//        String str = "";
        
        System.out.println(new leetcode_290_WordPattern().wordPattern(pattern, str));
    }
    
    public boolean wordPattern(String pattern, String str) {
       String pattern1 = getPattern(pattern, "");
       String pattern2 = getPattern(str, " ");
        
       return pattern1.equals(pattern2);
    }
    
    // 将每一个字符串都变成一个指定的模式, 看看两个字符串的模式是否相同
    // splite代表切割方式, 两个字符串的切割方式是不一样的
    public String getPattern (String str, String splite) {
        if (str.equals("")) {
            return "";
        }
        
        String[] strArr = str.split(splite);
        
        HashMap<String, Character> map = new HashMap<>();
        StringBuilder pattern = new StringBuilder();
        char c = 'a';
        for (int i = 0; i < strArr.length; i++) {
            Character curChar = map.get(strArr[i]);
            // 如果以该字符串为键是空, 则只要设置一个字符作为其值
            if (curChar == null) {
                pattern.append(c);
                map.put(strArr[i], c++);
            } else {
                pattern.append(curChar);
            }
        }
        
        return pattern.toString();
    }
}
