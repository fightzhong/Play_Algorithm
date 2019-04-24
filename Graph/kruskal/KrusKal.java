package kruskal;

import java.util.ArrayList;

import weightgraph.Edge;
import weightgraph.Graph;
import weightgraph.Iterators;

/**
 * 
 * KrusKal算法:
 * 	实现思路:
 * 			我们首先对所有的边进行排序, 然后依次获取最小的边将其添加到最小生成树中, 如果这个最小的边的添加会使得
 * 			这个最小生成树形成一个环, 那么这条边就是不可取的, 应该丢弃, 而如何判断是否会形成环呢, 需要用到并查集, 
 * 			形成环的情况只可能是在最小生成树中某两个顶点连接成的边, 所以一旦查到这两条边的顶点的根是同一个, 那么
 * 			就不能将这条边加入最小生成树, 每次在添加一条边到最小生成树后, 都要将这条边的两个顶点进行union操作
 * @author
 */

public class KrusKal<T extends Comparable<T>> {
	private Graph<T> graph;								// 图的副本
	private MinHeap<Edge<T>> heap;					// 最小堆
	private ArrayList<Edge<T>> minSpanningTree;	// 最小生成树
	
	public KrusKal (Graph<T> graph) {
		this.graph = graph;
		this.minSpanningTree = new ArrayList<Edge<T>>();
		this.heap = new MinHeap<Edge<T>>(graph.getEdge());
		
		// 将所有的边放入最小堆
		edgesSort();
		generateMinSpanningTree();
	}
	
	/**将所有的边放入最小堆**/
	private void edgesSort () {
		for (int i = 0; i < graph.getPeak(); i ++) {
			Iterators iterator = graph.iterator(i);
			while (!iterator.end()) {
				Edge<T> edge = (Edge<T>)iterator.next();
				if (edge.getFromPeak() < edge.getToPeak()) {
					heap.insert(edge);
				}
			}
		}
	}
	
	/**生成最小生成树**/
	private void generateMinSpanningTree () {
		// 获取一个并查集
		UnionFind unionFind = new UnionFind(graph.getPeak());
		
		while (minSpanningTree.size() < graph.getPeak() - 1) {
			// 依次取出最小边
			Edge<T> edge = heap.extractMin();
			
			// 如果该最小边的两个顶点的根顶点能够连成一个环, 则该边不能作为最小生成树的最小边
			boolean connected = unionFind.isConnected(edge.getFromPeak(), edge.getToPeak());
			if (!connected) {
				unionFind.union(edge.getFromPeak(), edge.getToPeak());
				minSpanningTree.add(edge);
			}
		}
	}
	
	public ArrayList<Edge<T>> getMinSpanningTree () {
		return minSpanningTree;
	}
	
	/**获取最小生成树的权值**/
	public Double getMinWeight () {
		double weight = 0;
		
		for (Edge<T> edge: minSpanningTree) {
			weight += (Double)edge.getWeight();
		}
		
		return weight;
	}
	
}
