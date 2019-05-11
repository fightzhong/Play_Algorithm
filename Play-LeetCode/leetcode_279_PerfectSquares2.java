import java.util.ArrayList;
import java.util.LinkedList;

/**
                  仍然是用图的广度优先遍历来进行解决, 不同的是这里不需要图, 
                  首先我们回忆一下, 我们对图进行广度优先遍历, 首先将顶点n, 层次为1压入队列
                  然后进入队列不为空的循环,此时我们需要取出队首的元素, 然后我们需要将与队首元素
                  的差是平方值的顶点放入队列, 然后再继续执行上述的逻辑
 */
public class leetcode_279_PerfectSquares2 {
    public static void main(String[] args) {
        System.out.println(new leetcode_279_PerfectSquares2().numSquares(52));
    }
    
    public int numSquares(int n) {
        
        // 开始进行广度优先遍历
        LinkedList<Element> queue = new LinkedList<Element>();
        queue.addLast(new Element(1, n));
        
        while (!queue.isEmpty()) {
            Element ele = queue.removeFirst();
            int level = ele.level;
            int peek = ele.peek;
            
            // 找到与当前顶点的差为平方值的顶点
            int base = 1;               
            int square = base * base;   // 依次减去1, 4, 9, 16.....直到当前平方值比顶点的值还大
            while (square <= peek) {
                int result = peek - square;
                if (result == 0) {
                    return level;
                }
                
                base ++;
                square = base * base;
                queue.addLast(new Element(level + 1, result));
            }
        }
        
        return 0;
    }
    
    class Element {
        int level;
        int peek;
        
        public Element(int level, int peek) {
            this.level = level;
            this.peek = peek;
        }
    }
}
