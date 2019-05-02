import java.util.ArrayList;
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
      
     思路:
        HashMap键值对:
                         键: 每个字符串的字符
                         值: 该字符出现的个数
              当键的个数和值的个数相同时, 可以认为这两个map是同一个
        依次遍历每一个字符串, 将满足上述条件的字符串放入一个List中
        同样的, 用一个键值对来存储这个关系, 键为上面的HashMap, 值为List             
 */
public class leetcode_049_GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        HashMap<HashMap<Character, Integer>, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            HashMap<Character, Integer> m = new HashMap<Character, Integer>();
            for (int j = 0; j < strs[i].length(); j++) {
                char c = strs[i].charAt(j);
                if (m.get(c) == null) {
                    m.put(c, 1);
                } else {
                    m.put(c, m.get(c) + 1);
                }
            }
            
            if (map.get(m) == null) {
                ArrayList<String> arr = new ArrayList<String>();
                arr.add(strs[i]);
                map.put(m, arr);
            } else {
                map.get(m).add(strs[i]);
            }
        }
        
        ArrayList<List<String>> list = new ArrayList<List<String>>();
        for (HashMap<Character, Integer> m: map.keySet()) {
            list.add(map.get(m));
        }
        
        return list;
    }
}
