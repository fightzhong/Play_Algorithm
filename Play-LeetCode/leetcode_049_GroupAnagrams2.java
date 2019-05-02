import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
      Description:
          Given an array of strings, group anagrams together.
     
      Example:
        Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
        Output:
        [
          ["ate","eat","tea"],
          ["nat","tan"],
          ["bat"]
        ]
      
      同上一版本的思路差不多, 但是这里是字符串作为键, 对每一个遍历的字符串进行了排序, ArrayList作为值
 */
public class leetcode_049_GroupAnagrams2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        ArrayList<List<String>> list = new ArrayList<List<String>>();
        
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String s: strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String str = Arrays.toString(arr);
            if (map.get(str) == null) {
                ArrayList<String> a = new ArrayList<String>();
                list.add(a);
                map.put(str, a);
            }
            map.get(str).add(s);
        }
        
        return list;
    }
}
