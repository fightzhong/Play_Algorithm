import java.util.LinkedList;

/**
          对第二种方法进行优化, 对于已经访问的不再访问
 */
public class leetcode_279_PerfectSquares3 {
    public static void main(String[] args) {
        System.out.println(new leetcode_279_PerfectSquares3().numSquares(52));
    }
    
    public int numSquares(int n) {
        
        // 开始进行广度优先遍历
        LinkedList<Element> queue = new LinkedList<Element>();
        queue.addLast(new Element(1, n));
        
        boolean[] visited = new boolean[n + 1];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        
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
                
                if (visited[result]) {
                    continue;
                }
                visited[result] = true;
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
