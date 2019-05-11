import java.util.ArrayList;
import java.util.LinkedList;

/**
      将该问题转换为图的广度优先遍历解决方式:
                  由于我们需要获取的是最少个数的值相加等于目标数, 而这个相加的值必须是一个平方数
                  那么我们就可以创建一个从0开始,顶点个数为目标数的无权图,这个无权图的创建是怎么样的呢
                  
                  首先我们需要从1开始遍历到目标数, 每两个顶点之间形成边的原则是这两个顶点的差能够构成一个平方数
                  由于我们仅仅需要考虑最少个数的平方数,所以就需要从0找到一个到该目标数的最短路径即可了
                  
                  那么怎么去判断这些边的添加呢, 我们可以通过目标数依次减去一个平方数,则减法运算的结果就是另一个顶点了
                  图可以用稀疏图来表示, 即数组链表的表示
                  
     但是该方法是可以进行优化的, 可以减少很多逻辑
 */
public class leetcode_279_PerfectSquares {
    public static void main(String[] args) {
        System.out.println(new leetcode_279_PerfectSquares().numSquares(52));
    }
    
    public int numSquares(int n) {
        // 用数组链表来表示一个稀疏图(注意: 这里需要开辟的空间是n + 1个)
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        
        // 初始化稀疏图
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        
        // 往图中添加边
        for (int i = 1; i < graph.length; i ++) {
            addEdge(graph, i);
        }
        
        // 开始进行广度优先遍历
        LinkedList<Element> queue = new LinkedList<Element>();
        queue.addLast(new Element(1, n));
        
        while (!queue.isEmpty()) {
            Element ele = queue.removeFirst();
            int level = ele.level;
            int peek = ele.peek;
            
            // 将该顶点下的所有相连接的顶点压入队列
            for (Integer i: graph[peek]) {
                if (i == 0) {
                    return level;
                }
                queue.addLast(new Element(level + 1, i));
            }
        }
        
        return 0;
    }
    
    /**在图中对顶点x添加到其它顶点的边**/
    public void addEdge (ArrayList<Integer>[] graph, int x) {
        int base = 1;
        int square = base * base; // 平方数
        
        // 添加边
        while (square <= x) {
            int peek = x - square;  // 用当前值 - 平方数, 则得出当前顶点到peek之间应该添加一条边
            graph[x].add(peek);
            
            // 获取下一个平方数
            base ++;
            square = base * base;
        }
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
