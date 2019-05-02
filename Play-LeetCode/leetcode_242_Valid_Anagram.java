import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;

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

// spendtime: 22ms
public class leetcode_242_Valid_Anagram {
    public static void main(String[] args) throws Exception {
        
        InputStream inputStream = new FileInputStream("./Play-LeetCode/s.txt");
        InputStream inputStream2 = new FileInputStream("./Play-LeetCode/t.txt");
        
        String s = new String(inputStream.readAllBytes());
        String t = new String(inputStream2.readAllBytes());
        
        System.out.println(new leetcode_242_Valid_Anagram().isAnagram(s, t));
    }
    
    public boolean isAnagram(String s, String t) {
       HashMap<Character, Integer> sMap = getMap(s);
       HashMap<Character, Integer> tMap = getMap(t);
       
       if (sMap.size() != tMap.size()) {
           return false;
       }
       
       Set<Character> set = sMap.keySet();
       for (char c: set) {
           Integer val1 = sMap.get(c);
           Integer val2 = tMap.get(c);
           if (val1 == null || val2 == null || (int)val1 != (int)val2) {
               return false;
           }
       }
       
        return true;
    }
     
    // 
    public HashMap<Character, Integer> getMap (String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            map.put(c, map.get(c) + 1);
        }
        
        return map;
    }
}

















