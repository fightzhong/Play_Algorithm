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

public class leetcode_076_MinimumWindowSubstring {
    public static void main(String[] args) {
        String s = "aa";
        String t = "aa";
//        String s = "cabwefgewcwaefgcf";
//        String t = "cae";     // "cwae"
//        String s = "ADOBECODEBANC";
//        String t = "ABC";       // BANC
        
        System.out.println(new leetcode_076_MinimumWindowSubstring().minWindow(s, t));
    }
    
    public String minWindow(String s, String t) {
        int len = 0;            // 满足添加的元素个数
        int[] charStandard = new int[256];
        int[] count = new int[256];
        int[] subString = null;
        
        for (int i = 0; i < t.length(); i++) {
            charStandard[t.charAt(i)]++;
        }
        
        int l = 0;
        int index = 0;
        int range = s.length() + 1;
        
        // 遍历每一个元素
        while (index < s.length()) {
            // 如果此时len是0, 说明还没有出现一个满足条件的元素, 此时应该同时去维护l的值
            char c = s.charAt(index);
            if (charStandard[c] == 0) {
                if (len == 0) {
                    l++;
                }
                index++;
                continue;
            } else {        // ADOBECODE BANC  ABC
                // 当前的元素属于字符串t中, 此时应该判断是否满足个数要求
                if (count[c] == charStandard[c]) { // 说明字符c的个数已经满了, 此时应该将l 指针指向字符c第一次出现后下一个满足条件的元素
                    // 先找到字符c第一次出现的位置
                    while (true) {
                        char curC = s.charAt(l);
                        if (curC != c) {
                            if (count[curC] > 0) {
                                count[s.charAt(l)]--;
                                len--;
                            }
                            l++;
                        } else {
                            l++;
                            break;
                        }
                    }
                    
                    // 此时找到了c字符第一次出现的位置, 并且index指向了c字符的下一个位置
                    // 接下来l 应该指向下一个存在于字符串t中的元素
                    while (charStandard[s.charAt(l)] <= 0) {
                        l++;
                    }
                } else {
                   // 这时说明当前正在查看的字符是还可以算在内的, 此时应该先加入当前字符, 然后判断是否已经找到了字串
                    count[c]++;
                    len++;
                    // 如果找到了子串, 更新range, 同时将l移到下一个满足条件的位置
                    if (len == t.length()) {
                        int curRange = index - l + 1;
                        if (curRange < range) {
                            range = curRange;
                            subString = new int[] {l, range};
                        }
                        // 将处于l元素的count--, 同时使得len--
                        count[s.charAt(l)]--;
                        len--;
                        while (l + 1 < s.length() && charStandard[s.charAt(++l)] <= 0);
                    }
                }
                index++;
            }
        }
        
        return subString == null ? "" : s.substring(subString[0], subString[0] + subString[1]);
    }
}
