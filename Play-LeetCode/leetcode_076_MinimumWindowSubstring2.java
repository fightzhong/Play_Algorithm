import java.util.ArrayList;
import java.util.Arrays;

/**
 	Description:
	 	Given a string S and a string T, find the minimum window in S which will contain
	 	all the characters in T in complexity O(n).

 	Example:
        Input: S = "ADOBECODEBANC", T = "ABC"
        Output: "BANC"
 */

public class leetcode_076_MinimumWindowSubstring2 {
    public static void main(String[] args) {
//        String s = "aa";
//        String t = "aa";
//        String s = "cabwefgewcwaefgcf";
//        String t = "cae";     // "cwae"
//        String s = "ADOBECODEBANC";
//        String t = "ABC";       // BANC
        String s = "aaaaaaaaaaaabbbbbcdd";
        String t = "abcdd";       // "abbbbbcdd"
        
        System.out.println(new leetcode_076_MinimumWindowSubstring2().minWindow(s, t));
    }
    
    public String minWindow(String s, String t) {
        int len = 0;                        // 满足添加的元素个数
        int[] charStandard = new int[256];
        int[] count = new int[256];
        int[] subString = null;
        
        for (int i = 0; i < t.length(); i++) {
            charStandard[t.charAt(i)]++;
        }
        
        int l = 0;                  // 滑块的左边界
        int index = 0;              // 遍历的索引
        int range = s.length() + 1; // 最小范围
        
        // 遍历每一个元素
        while (index < s.length()) {
            // 如果此时len是0, 说明还没有出现一个满足条件的元素, 此时应该同时去维护l的值
            char c = s.charAt(index);
            if (charStandard[c] == 0) {     
                if (len == 0) {
                    l++;
                }
            } else {
                count[c]++;
                // 如果还是在范围内, 那么此时维护len值
                if (count[c] <= charStandard[c]) {
                    len++;
                }
                
                // 当len == t.length()的时候, 说明此时还是满足的子串, 但是不一定是最短的子串
                while (len == t.length()) {
                    int curRange = index - l + 1;
                    if (curRange < range) {
                        range = curRange;
                        subString = new int[] {l, range};
                    }
                    
                    // 使得当前字符的个数减一, 然后判断是否还是子串, 如果是, 那么就应该找到下一个属于字符串t中的
                    // 字符, 然后继续判断len的值, 如果不是, 那么就应该减少len的值, 然后index继续查找后面的元素
                    count[s.charAt(l)]--;
                    if (count[s.charAt(l)] < charStandard[s.charAt(l)]) {
                        len--;
                    }
                    
                    // 找到下一个属于字符串t中的字符, 找到后l指向的就是该字符了
                    while (l + 1 < s.length() && charStandard[s.charAt(++l)] <= 0);
                }
            }
            index++;
        }
        
        return subString == null ? "" : s.substring(subString[0], subString[0] + subString[1]);
    }
}
