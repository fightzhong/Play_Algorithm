import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
/**
 
     可以通过下面的例子来看思路:
      beginWord = "hit",
      endWord = "cog",
      wordList = ["hot","dot","dog","lot","log","cog"]
       
          每一组元素都与前一个元素相差一个字符        
      hit => hot => dot, lot => dog, log => cog
           
 
         思路:
         Element对象保存了当前字符串和更换位置的次数
                由于我们每次都只更换一个字符, 那么我们从beginWord开始, 将其压入队列
                从队首取出元素A, 然后对整个wordList进行遍历, 找到与元素A相差一个字符的, 判断是否已经添加过该元素
                到队列,这里可用HashMap来存储是否被访问, 如果已经访问则跳过, 防止dot,lot这样的, dot添加lot, lot添加
         dot, 从而导致死循环, 如果没访问过, 将其封装为Element对象, 设置其更换位置次数加一, 然后压入队列
         
                直到找到endWord为止, 如果队列都为空了还没找到就返回0
                
 
         非最佳答案
 */
public class leetcode_127_WordLadder {
    public static void main(String[] args) {
        
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log");
        System.out.println(new leetcode_127_WordLadder().ladderLength("hit", "cog", wordList));
    }
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, Boolean> map = new HashMap<>();
        
        LinkedList<Element> queue = new LinkedList<>();
        queue.add(new Element(beginWord, 1));
        
        while (!queue.isEmpty()) {
            Element ele = queue.removeFirst();
            String str = ele.str;
            int count = ele.count;
            
            for (String s: wordList) {
                if (map.containsKey(s)) {
                    continue;
                }
                
                int num = 0;
                for (int i = 0; i < s.length(); i ++) {
                    if (s.charAt(i) != str.charAt(i)) {
                        num++;
                    }
                }
                
                if (num == 1) {
                    if (endWord.equals(s)) {
                        return count + 1;
                    }
                    queue.addLast(new Element(s, count + 1));
                    map.put(s, true);
                }
            }
            
        }
        
        return 0; 
    }
    
    class Element {
        String str;
        int count;
        
        public Element(String str, int count) {
            this.str = str;
            this.count = count;
        }
    }
}
