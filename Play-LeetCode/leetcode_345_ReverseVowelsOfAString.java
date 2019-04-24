import java.util.ArrayList;
import java.util.Arrays;

/**
 	Description:
	 	Write a function that takes a string as input and reverse only the vowels of a string.

  Example 1:
    Input: "hello"
    Output: "holle
    
  Example 2:
    Input: "leetcode"
    Output: "leotcede"
 */
public class leetcode_345_ReverseVowelsOfAString {
  public static void main(String[] args) {
    String s = "hello";
    System.out.println(new leetcode_345_ReverseVowelsOfAString().reverseVowels(s));
  }
  
  public String reverseVowels(String s) {
    char[] strArr = s.toCharArray();
    int l = 0;
    int r = s.length() - 1;
    
    while (l < r) {
      while (l < r && !isVowel(strArr[l])) {
        l++;
      }
      
      while (l < r && !isVowel(strArr[r])) {
        r--;
      }
      
      if (l > r) {
        return getString(strArr);
      }
      
      swap(strArr, l++, r--);
    }
    
    return getString(strArr);
  }
  
  public boolean isVowel (char c) {
    return c == 97 || c == 101 || c == 105 || c == 111 || c == 117 ||
           c == 65 || c == 69 || c == 73 || c == 79 || c == 85;
  }
  
  public void swap (char[] arr, int i, int j) {
    char temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
  
  public String getString (char[] arr) {
    StringBuilder str = new StringBuilder();
    for (char c: arr) {
      str.append(c);
    }
    
    return str.toString();
  }
}
