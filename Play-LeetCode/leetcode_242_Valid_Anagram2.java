import java.util.Arrays;

/**
 	Description:
	 	Given two strings s and t , write a function to determine if t is an anagram of s.

 	Example 1:
        Input: s = "anagram", t = "nagaram"
        Output: true
        
    Example 2:
        Input: s = "rat", t = "car"
        Output: false    
 
 */

// spendtime: 6ms
public class leetcode_242_Valid_Anagram2 {
    public boolean isAnagram(String s, String t) {
        char[] schar = s.toCharArray();
        char[] tchar = t.toCharArray();
        Arrays.sort(schar);
        Arrays.sort(tchar);
        if (schar.length != tchar.length) {
          return false;
        }
        
        for (int i = 0; i < schar.length; i++){
          if (schar[i] != tchar[i]) {
            return false;
          }
        }
          
        return true;
    }
     
}

