import java.util.ArrayList;

public class leetcode_279_PerfectSquares {
    private class Edge {
        int from;
        int to;
        int edge;
        
        public Edge (int from, int to, int edge) {
            this.from = from;
            this.to = to;
            this.edge = edge;
        }
    }
    
    public int numSquares(int n) {
       ArrayList<Edge>[] graph = new ArrayList[n];
       for (int i = 0; i < graph.length; i++) {
           graph[i] = new ArrayList<Edge>();
       }
       for (int i = 1; i < n; i++) {
           int index = 1;
           int squareVal = index * index;
           while (squareVal <= n) {
               int point = n - squareVal;
               graph[n].add(new Edge(n, point, squareVal));
               
               index++;
               squareVal = index * index;
           }
           
           
           
       }
       
        
        
        
        
        
        return 0;
    }
}
