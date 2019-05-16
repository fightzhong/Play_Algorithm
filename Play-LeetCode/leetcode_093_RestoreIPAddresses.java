import java.util.ArrayList;
import java.util.List;

/**
  合法的IP是分为4列的, 每一列的值不能大于255
  getValidIP用于获取当前列中可能出现情况
  参数分析:
      index: 表示当前列中开始的索引
      s: 即为整个字符串
      fromStr: 表示前面的几列形成的合法IP
      count: 表示第几列
      
      对每一列可以有三种情况, 一个元素, 两个元素, 三个元素
      对这每一个元素进行判断, 如果小于255, 那么就是合法的IP段
     
     
     特殊情况:
        <1> 对于最后一列的IP, 直接截取index 到 s.length() 的范围的字符,
                同时, 其不应该有下一列了
        <2> 对于当前得到的字符串来说, 如果其个数是大于1个的, 并且其第一个数是0的, 我们也应该跳过
  
 */

public class leetcode_093_RestoreIPAddresses {
    public static void main(String[] args) {
        System.out.println(new leetcode_093_RestoreIPAddresses().restoreIpAddresses("010010"));
    }
    
    public List<String> restoreIpAddresses(String s) {
        ArrayList<String> list = new ArrayList<String>();
        getValidIP( 0, s, "", 1, list );
        return list;
    }
    
    public void getValidIP (int index, String s, String fromStr, int count, ArrayList<String> list) {
        // 最后一位开始超过3个IP值, 或者 当前索引已经超过了字符串的长度, 此时都已经是不合法的IP了
        if ( ( index >= s.length() ) || ( count == 4 && s.length() - index > 3 ) ) {
            return;
        }
        
        // 每一列最多出现三个数值
        for ( int i = 0; i < 3; i ++ ) {
            // 截取字符串的末尾
            int tail = index + i + 1;
            // 如果末尾超过了字符串的长度, 那么就直接返回
            if ( index + i + 1 > s.length() ) {
                return;
            }
            
            // 如果到了最后一列, 此时应该将字符串最后所有的字符进行截取
            if ( count == 4 ) {
                tail = s.length();
            }
            
            String curStr = s.substring( index, tail );
            
            // 对于当前得到的字符串来说, 如果其个数是大于1个的, 并且其第一个数是0的, 我们也应该跳过
            if ( curStr.length() > 1 && curStr.charAt(0) == '0' ) {
                return;
            }
            
            // 当前位置合法的IP
            if ( Integer.parseInt(curStr) <= 255 ) {
                
                // 到了第四列的IP, 并且是合法的
                if ( count == 4 ) {
                    list.add( fromStr + curStr );
                    return;
                }
                
                getValidIP( tail, s, fromStr + curStr + ".", count + 1, list );
            }
        }
    }
}
