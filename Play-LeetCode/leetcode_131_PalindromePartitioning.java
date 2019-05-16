import java.util.ArrayList;
import java.util.List;

public class leetcode_131_PalindromePartitioning {
    public static void main(String[] args) {
        System.out.println(new leetcode_131_PalindromePartitioning().partition("aab"));
    }
    
    public List<List<String>> partition(String s) {
        ArrayList<List<String>> list = new ArrayList<List<String>>();
        getList(s, 0, "", list);
        return list;
    }
    
    public void getList (String s, int index, String preStr, ArrayList<List<String>> list) {
        if ( index >= s.length() ) {
            return;
        }
        
        // 从当前索引开始, 截取一个,二个...一直截取到最后的字符串
        for ( int i = 0; i < s.length() - index; i ++ ) {
            int tail = index + i + 1;
            
            String sub = s.substring( index, tail);
            // 如果是回文串, 那么就应该以其下一个元素开始截取, 直到截取到最后
            if ( isPalindrome(sub) ) {
                // 此时说明最后一个子串已经是回文串了
                if ( tail == s.length()) {
                    String[] strArr = (preStr + sub).split(",");
                    // 将数组中字符串放入到list中
                    ArrayList<String> subList = new ArrayList<String>();
                    for ( String str: strArr ) {
                        subList.add(str);
                    }
                    
                    list.add(subList);
                } else {
                    getList( s, tail, preStr + sub + ",", list );
                }
                
            }
        }
    }
    
    public boolean isPalindrome (String s) {
        int l = 0;
        int r = s.length() - 1;
        
        while ( l < r ) {
            if ( s.charAt(l++) != s.charAt(r--) ) {
                return false;
            }
        }
        
        return true;
    }
}
