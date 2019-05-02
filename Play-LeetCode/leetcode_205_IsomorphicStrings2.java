import java.util.HashMap;

/**
     Description:
         Given two strings s and t, determine if they are isomorphic.
         Two strings are isomorphic if the characters in s can be replaced to get t.
         All occurrences of a character must be replaced with another character while 
         preserving the order of characters. No two characters may map to the same character 
         but a character may map to itself.
     
     Example 1:
        Input: s = "egg", t = "add"
        Output: true
        
    Example 2:
        Input: s = "foo", t = "bar"
        Output: false
 
 
 */
public class leetcode_205_IsomorphicStrings2 {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        int[] sArr = new int[127];
        int[] tArr = new int[127];
        
        for (int i = 0; i < s.length(); i++) {
            if (sArr[s.charAt(i)] != tArr[t.charAt(i)]) {
                return false;
            }
            sArr[s.charAt(i)]  = i;
            tArr[t.charAt(i)]  = i;
        }
        
        return true;
    }
}
