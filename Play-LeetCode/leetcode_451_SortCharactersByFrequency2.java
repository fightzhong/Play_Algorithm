import java.util.ArrayList;
import java.util.PriorityQueue;

/**
     Description:
         Given a string, sort it in decreasing order based on the frequency of characters.
     
     Example 1:
        Input:
            "tree"
        Output:
            "eert"
        Explanation:
            'e' appears twice while 'r' and 't' both appear once.
             So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 
 
 */
public class leetcode_451_SortCharactersByFrequency2 {
    public static void main(String[] args) {
        String s = "cccaaa";
        System.out.println(new leetcode_451_SortCharactersByFrequency2().frequencySort(s));
    }
    public String frequencySort(String s) {
        
        int[] count = new int[127];       // 每个字符出现的频率
        int largeCount = 0;               // 最大出现的频率
        // 获取每一个字符出现的频率 O(n)
        for (int i = 0; i < s.length(); i++) {
            int curChar = s.charAt(i);
            count[curChar]++;
            largeCount = largeCount < count[curChar] ? count[curChar] : largeCount;
        }
        
        // 存储从频率1-largeCount的字符 O(n)
        ArrayList<Character>[] freq = (ArrayList<Character>[])new ArrayList[largeCount + 1];
        for (int i = 0; i < count.length; i++) {
            int nums = count[i];
            if (nums != 0) {
                if (freq[nums] == null) {
                    freq[nums] = new ArrayList<Character>();
                }
                freq[nums].add((char)i);
            }
        }
        
        StringBuilder str = new StringBuilder();
        for (int i = freq.length - 1; i >= 1 ; i--) {
            if (freq[i] != null) {      // 只有在存在频率i次的情况下才去遍历
                for (char c: freq[i]) {
                    for (int j = 0; j < i; j++) {
                        str.append(c);
                    }
                }
            }
        }
        
        return str.toString();
    }
}

