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
public class leetcode_242_Valid_Anagram3 {
    public boolean isAnagram(String s, String t) {
        int[] sCount = new int[127];
        int[] tCount = new int[127];

        if (s.length() != t.length()) {
          return false;
        }

        for (int i = 0; i < s.length(); i++) {
          sCount[s.charAt(i)]++;
          tCount[t.charAt(i)]++;
        }

        for (int i = 0; i < sCount.length; i++) {
          if (sCount[i] != tCount[i]){
            return false;
          }
        }

        return true;   
    }
     
}

