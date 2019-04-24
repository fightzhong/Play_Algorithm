package prim;

import java.util.ArrayList;
import java.util.PriorityQueue;

import weightgraph.Edge;
import weightgraph.Graph;
import weightgraph.Iterators;

/**
 * 最小生成树:
 * 		描述: 指的是在一个图中找到一个可以让所有节点连接起来的最短路径, 可用于在电缆中, 当对多个地方进行布置电缆
 * 				的时候, 为了节省开销, 不必让每一个地方都与其它所有地方连接, 只需要将这些地方依次连接起来, 这样就可以
 * 				使得所有的地方能够连上电缆并且花销最小			
 * Prim算法: 
 * 		指的是在一个有权图中, 查找一条路径使得能够连接所有的顶点, 并且该路径的权重是所有路径中最小的
 * 		
 * 	分析:
 * 		切分: 指的是将一个图分为两份, 这两份可以是任意大小, 并且这两份图能够有一条或者多条边将它们连接起来, 将图
 * 				分为两份就是切分的意思
 * 		切边: 连接两份图的边即为切边, 切边可以有多条
 * 		横切边: 指的是两份图中连接的所有边中最短的那一条
 * 		结论: 横切边必定是最小生成树中的其中一条边
 * 		
 * 	实现思路:
 * 		由于横切边的存在, 那么我们可以将0号顶点作为a图, 其它顶点作为b图, 那么从a -> b 中最小的那条边就是最小生成树中的其中一条边, 以此类推
 * 		当a图每增加一个顶点, 则a图与其它图之间的横切边就变成这两个顶点与其它图之间的最小边
 * 		<1> 从原点(0号顶点)出发, 获取其与其它顶点的所有的边, 将这些边压入最小堆中, 然后取出最小的边, 并且将最小边对应的另外一个顶点作为下一次
 * 		       判断的点	
 * 		<2> 从刚才获取的顶点开始, 将其所有的边都压入最小堆中, 然后再取出最小的边, 并将该最小的边对应的另外一个顶点作为下一次判断的点
 * 		<3> 依次类推, 当所有的点都访问过后, 同时也获取了连接这些顶点的最小边, 如果在过程中遇到了已经访问过的点, 那么就将其忽略, 并获取下一个最小的值
 * 
 * 	时间复杂度分析:
 * 		我们在实现最小生成树的过程中, 需要将每一条边都遍历一边., 其次对每一条边的操作中, 都涉及到了堆的操作, 由于堆是用来存放边的, 所以在最坏的情况下,
 * 		每个顶点与所有的顶点相连, 那么在遍历一个顶点的时候就需要遍历该顶点下所有相连的顶点, 一共V个, 同时每一个相连的顶点, 都执行堆操作, 每一个堆操作
 * 		都是log(E)级别, 所以一次外层循环, 内部最坏情况可能是Vlog(E), 一共有V次循环, 所以时间复杂度是O(V*VlogE), 由于V*V其实就是边的个数, 所以为O(ElogE)级别
 * 
 * @author 
 */
// 补充泛型
public class Prim {
	private Graph graph;								// 要获取最小生成树的图
	private PriorityQueue<Edge> minHeap;		// 最小堆, 优先队列, Java自带的优先队列是最小堆实现的
	private ArrayList<Edge> minSpanningTree;	// 最小生成树的所有边
	private boolean[]	visited;						// 顶点是否已经访问过

	public Prim (Graph<? extends Comparable<?>> graph) {
		this.graph = graph;
		this.minHeap = new PriorityQueue<Edge>();
		this.minSpanningTree = new ArrayList<Edge>();
		this.visited = new boolean[graph.getPeak()];
		
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}
		
		generateMinSpanningTree();
	}
	
	private void generateMinSpanningTree () {
		// 从树的原顶点开始出发查找
		int index = 0;
		
		// 当所有顶点都被遍历之后, 最小生成树也就找到了, 边的数量应该是顶点的数量减一
		while (minSpanningTree.size() < graph.getPeak() - 1) {
			// 对该顶点进行迭代, 将其所有边都压入最小堆中 
			Iterators iterator = graph.iterator(index);
			
			while (!iterator.end()) {
				Edge next = iterator.next();
				// 只将index顶点到一个未访问过的顶点的边压入最小堆
				if (!visited[next.getToPeak()]) {
					minHeap.add(next);
				}
			}
			
			// 设置该顶点为已经访问过的, 这样下次其它顶点在迭代的时候就会忽略那些顶点到index的边
			visited[index] = true;
			
			// 此时取出最小的边, 如果这个最小边的两个顶点都被访问过了, 那么就提取下一个最小边
			// 直到一个被访问一个没被访问则才是横切边, 该边就是最小生成树中的一条边, 并放入最小生成树中
			Edge minEdge;
			while (true) {
				minEdge = minHeap.remove();
				if (visited[minEdge.getFromPeak()] && visited[minEdge.getToPeak()]) {
					continue;
				}
				break;
			}
			
			minSpanningTree.add(minEdge);
			
			// 将index设置为该最小边中没被访问过的顶点
			index = visited[minEdge.getToPeak()] == true ? minEdge.getFromPeak() : minEdge.getToPeak();
		}
	}
	
	public ArrayList<Edge> getMinSpanningTree () {
		return minSpanningTree;
	}
	
	/**获取最小生成树的权值**/
	public Double getMinWeight () {
		double weight = 0;
		
		for (Edge edge: minSpanningTree) {
			weight += (Double)edge.getWeight();
		}
		
		return weight;
	}
}
























