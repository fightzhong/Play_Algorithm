package dijkstra;

import java.util.ArrayList;

import weightgraph.Edge;
import weightgraph.Graph;
import weightgraph.Iterators;

/**
 * 
 * 最短路径算法: 
 * 		对于无权图来说, 因为深度的固定, 所以其最短路径就是深度的最小, 通过广度优先遍历, 每次遍历一个顶点都让与该顶点
 * 		相连的顶点设置from的值为该顶点, 从而可以求出最短深度
 * 
 *	有权图最短路径算法(dijkstra算法):
 *			前提: 无负权边的存在
 *			实现思路:
 *					两个顶点之间的最短路径, 则以其中一个顶点开始对其所有连接的顶点进行遍历, 则其第一次遍历就会使得
 *					获取到该顶点到其它相邻节点的权值, 那么对于这些权值来说, 权值最小的(顶点a -> 顶点b)一定是a顶点
 *					到b顶点的最短路径, 因为其它权值都比这个值大的情况下, 由于不存在负权边, 所以不能通过其它顶点中转到
 *					b顶点, 接下来再从该顶点出发, 继续上述操作, 对于还没赋值的顶点来说, 直接赋值当作临时的最小路径
 *
 *					然后以b顶点出发遍历的所有相邻顶点, 权值最小的也是从a顶点到该顶点的最短路径, 而对于其它已经赋值的顶点
 *					来说, 此时需要判断是否当前中转路径的权值的大小和原来的权值大小的比较, 小于原来的, 就要去更新!
 * @author
 */
/**求得从顶点0到任意一个顶点的最短路径, 单源最短路径算法**/
public class Dijkstra<T extends Comparable<T>> {
	private Graph<T> graph;					// 图的引用
	private boolean[] visited;				// true则该顶点已经找到最短路径
	private int[] from;						// 值为当前索引所在的顶点的最短路径的上一层, 比如索引为3的值为5, 即顶点3的上一层是5
	private double[] weight;				// 每一个顶点对应的权值
	
	public Dijkstra (Graph<T> graph) {
		this.graph = graph;
		this.visited = new boolean[graph.getPeak()];
		this.from = new int[graph.getPeak()];
		this.weight = new double[graph.getPeak()];
		
		// 初始化visited, from数组
		for (int i = 0; i < graph.getPeak(); i ++) {
			visited[i] = false;
			from[i] = -1;
			weight[i] = -1;
		}
		
		generateMinSpanningTree();
	}
	
	private void generateMinSpanningTree () {
		IndexMinHeapOptimized<Double> heap = new IndexMinHeapOptimized<>(graph.getPeak());

		// 初始化顶点0的数据
		int index = 0;
		from[index] = 0;
		visited[index] = true;
		weight[index] = 0;
		
		while (true) {
			// 对每一个最短路径存在的顶点
			Iterators iterator = graph.iterator(index);
			
			while (!iterator.end()) {
				Edge<T> edge = (Edge<T>)iterator.next();	// index顶点的每一条邻边
				int curPeak = edge.getToPeak();				// 相邻顶点
				Double w = (Double)edge.getWeight();		// 相邻边的权值, 转为double
				
				// 如果该顶点已经找到了最短路径, 那么就跳过本次循环
				if (visited[curPeak]) {
					continue;
				}
				
				// 说明是第一次访问到该顶点, 那么这条边的权值就应该作为该顶点的临时最小路径
				if (weight[curPeak] == -1) {
					weight[curPeak] = w + weight[index];
					heap.insert(curPeak, weight[curPeak]);
					from[curPeak] = index;
				} else {
					// 不是第一次访问, 说明其已经存在了最短路径, 此时需要看情况是否应该更新权值
					double newWeight = (Double)w + weight[index];	// 新的权值 = 当前边的权值 + 上一个顶点的权值
					
					if (newWeight < weight[curPeak]) {
						weight[curPeak] = newWeight;
						heap.change(curPeak, newWeight);
						from[curPeak] = index;
					}
				}
			}
			
			// 循环结束, 说明index顶点下所有的相邻顶点的临时最短路径都已经更新, 此时权值最小的一定是index到该顶点的最短路径
			index = heap.extractMinIndex();
			visited[index] = true;
			
			// 如果堆已经为空了, 那么就终止循环
			if (heap.isEmpty()) {
				break;
			}
		}
	}
	
	/**获取从顶点0到顶点index的最短路径**/
	public String getShortestPath (int index) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		while (from[index] != index) {
			path.add(index);
			index = from[index];
		}
		
		path.add(0);
		 
		StringBuilder str = new StringBuilder();
		for (int i = path.size() - 1; i >= 0; i --) {
			str.append(path.get(i));
			if (i >= 1) {
				str.append(" -> ");
			}
		}
		
		return str.toString();
	}
	
	/**获取从顶点0到顶点index最短路径的权值**/
	public double getWeight (int index) {
		return weight[index];
	}
}




















