/*  
   Description: Given a string, determine if it is a palindrome, considering
   only alphanumeric characters and ignoring cases. Note: For the purpose of
   this problem, we define empty string as valid palindrome.
   
   Example 1: 
     Input: "A man, a plan, a canal: Panama"
     Output: true
   
   Example 2: 
     Input: "race a car" 
     Output: false
 */
public class leetcode_125_ValidPalindrome {
  public static void main(String[] args) {
    String s = "A man, a plan, a canal: Panama";
    System.out.println(isPalindrome(s));
  }

  public static boolean isPalindrome(String s) {
    s = s.toLowerCase();
    if (s.length() == 0) {
      return true;
    }

    int l = 0; // 字符串最左边的索引
    int r = s.length() - 1; // 字符串最右边的索引

    while (l < r) {
      char leftChar = s.charAt(l);
      char rightChar = s.charAt(r);
      
      if (!isPalindrome(leftChar)) {
        l++;
      }

      if (!isPalindrome(rightChar)) {
        r--;
      }

      if (isPalindrome(leftChar) && isPalindrome(rightChar)) {
        if (leftChar == rightChar) {
          l++;
          r--;
        } else {
          return false;
        }
      }
    }

    return true;
  }

  public static boolean isPalindrome(char c) {
    return (c >= 65 && c <= 90) || (c >= 48 && c <= 57);
  }
}
