package bellmanford;

import java.util.ArrayList;
import java.util.LinkedList;

import weightgraph.Edge;
import weightgraph.Graph;
import weightgraph.Iterators;

public class BellmanFord<T extends Comparable<T>> {
	private Graph<T> graph;					// 图的引用
	private int[] from;						// 顶点的来向
	private double[] weight;				// 各个顶点到原点的最短路径
	private boolean hasNegativeCycle; 	// 是否存在负权环 
	
	public BellmanFord (Graph<T> graph) {
		this.graph = graph;
		this.from = new int[graph.getPeak()];
		this.weight = new double[graph.getPeak()];
		
		// 初始化所有的顶点的来源为-1, 以及所有顶点的权重为无穷大
		for (int i = 0; i < graph.getPeak(); i++) {
			from[i] = -1;
			weight[i] = 999999999;
		}
		
		hasNegativeCycle = false;
		generateShortestPath();
	}
	
	/**生成从原点到任意一点的单源最短路径**/
	public void generateShortestPath () {
		// 初始化顶点0
		from[1] = 0;
		weight[1] = 0;
		LinkedList<Integer> queue = new LinkedList<>();
		queue.addLast(1);
		
		while (!queue.isEmpty()) {
			int index = queue.removeFirst();
			
			Iterators iterator = graph.iterator(index);
			while (!iterator.end()) {
				Edge<T> edge = (Edge<T>)iterator.next();
				int toPeak = edge.getToPeak();
				double w = (Double)edge.getWeight();
				
				if (toPeak == from[edge.getFromPeak()]) {
					hasNegativeCycle = true;
					return;
				}
				
				// 如果当前顶点的权值加上上一层的权值小于weight种当前顶点的权值, 就进行替换
				if (w + weight[index] < weight[toPeak]) {
					weight[toPeak] = w + weight[index];
					from[toPeak] = index;
				}
				
				queue.addLast(toPeak);
			}
		}
	}
	
	// 获取指定顶点的最短路径权重
	public double getWeight (int i) {
		if (hasNegativeCycle) {
			throw new IllegalArgumentException("the weighted graph has nagatived cycle!");
		}
		
		return weight[i];
	}
	
	// 获取指定顶点的最短路径
	public String showShortestPath (int i) {
		if (hasNegativeCycle) {
			throw new IllegalArgumentException("the weighted graph has nagatived cycle!");
		}
		
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		while (from[i] != i) {
			if (i == -1) {
				path.add(i);
				break;
			}
			path.add(i);
			i = from[i];
		}
		
		path.add(0);
		 
		StringBuilder str = new StringBuilder();
		for (int j = path.size() - 1; j >= 0; j --) {
			str.append(path.get(j));
			if (j >= 1) {
				str.append(" -> ");
			}
		}
		
		return str.toString();
	}
}
