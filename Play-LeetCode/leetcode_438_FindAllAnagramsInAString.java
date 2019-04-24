import java.util.ArrayList;
import java.util.List;

/**
 	Description:
	 	Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
        Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
        The order of output does not matter.
        
        
    Example 1:
        Input:
        s: "cbaebabacd" p: "abc" 
         
        Output:
        [0, 6]
        Explanation:
        The substring with start index = 0 is "cba", which is an anagram of "abc".
        The substring with start index = 6 is "bac", which is an anagram of "abc".
 */

public class leetcode_438_FindAllAnagramsInAString {
    public static void main(String[] args) {
        String s = "abacbabc";
//        String s = "abab";
        String p = "abc";
        System.out.println(new leetcode_438_FindAllAnagramsInAString().findAnagrams(s, p));
    }
    
    public List<Integer> findAnagrams(String s, String p) {
        
        List<Integer> arr = new ArrayList<Integer>();
        int[] countStandard = new int[127];         // 字符串中字符出现的次数情况
        int[] count = new int[127];                 // 我们遍历过程中出现的次数情况
        // 初始化字符串p中的每个字符出现的次数
        for (char c: p.toCharArray()) {
            countStandard[c] += 1;               
        }
        int l = 0;      // 左边界设为0              
        int r = -1;     // 右边界的遍历开始
        
        // r从左往右遍历, 查看字串的情况
        while (l < s.length() && r + 1 < s.length()) {      // abacbabc  abc
            // 先让右边界往后移动一位
            r++;
            count[s.charAt(r)] += 1;            // 个数加1
            
            // 个数处于有效范围
            if (count[s.charAt(r)] <= countStandard[s.charAt(r)]) {
                // 已经找到了相同的子串, 则保存当前字串的开始索引, 同时l往后移动一个位置, 使得能够判断下一个字串, l位置的元素的个数减一
                if (r - l + 1 == p.length()) {
                    arr.add(l);
                    l++;
                    count[s.charAt(l - 1)]--;
                }
            } else {
                // 个数不是处于有效范围, 此时应该判断当前r处于的值是否是字串中的值
                if (countStandard[s.charAt(r)] > 0) {
                    while (s.charAt(l) != s.charAt(r)) {
                        count[s.charAt(l)]--;
                        l++;
                    }
                    // 此时l指向的是个数超了的那个元素, 应该先对那个元素的个数减一, 然后从该元素的下一个元素开始考虑
                    count[s.charAt(l)]--;
                    l++;
                } else {
                    while (l < r) {
                        count[s.charAt(l)]--;
                        l++;
                    }
                    l = r + 1;
                }
            }
        }
        
        
        return arr;
    }
}
