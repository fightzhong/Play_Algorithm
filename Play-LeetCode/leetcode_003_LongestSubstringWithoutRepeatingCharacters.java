import java.util.Arrays;

/**
 	Description:
	 	Given a string, find the length of the longest substring without repeating characters.

 	Example 1:
        Input: "abcabcbb"
        Output: 3 
        Explanation: The answer is "abc", with the length of 3. 
 */

public class leetcode_003_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
//        String s = "abcabcbb";      // 3
//        String s = "bbbbb";       // 1
//        String s = "pwwkew";
//        String s = "au";
//        String s = "aab";
//        String s = "dvdf";
//        String s = "abcb";
        String s = "tmmzuxt";
        int result = new leetcode_003_LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring(s);
        System.out.println(result);
    }
    
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        
        char[] charArr = s.toCharArray();
        boolean[] isExist = new boolean[127];       // 用来存储该元素在当前字串中是否重复 
        int l = 0;
        int len = 1;
        isExist[charArr[l]] = true; // pwwkew
        
        for (int i = 1; i < charArr.length; i++) {
            char c = charArr[i];
            if (isExist[c]) {
                len = i - l > len ? i - l : len;
                while (charArr[l] != c) {
                    isExist[charArr[l]] = false;
                    l++;
                }
                l++;
            }
            isExist[c] = true;
        }
        
        if (l < charArr.length - 1) {
            len = charArr.length - l > len ? charArr.length - l : len;
        }
        
        return len;
    }
}
