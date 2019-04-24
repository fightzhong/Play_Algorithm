package prim;
import java.util.ArrayList;

import weightgraph.Edge;
import weightgraph.Graph;
import weightgraph.Iterators;

/**
 * 	第一个版本的Prim算法, 存在一个弊端, 我们在对当前顶点的边进行遍历的时候, 会把每一条边都放入堆中, 
 * 	而有些边是一定不会成为最小生成树的边的
 * 	
 * 	比如:
 * 			存在这么一种关系: 当从0开始遍历的时候, 0有4条边, 分别指向1, 2, 3, 4, 权值为3.5, 3.5, 3.5, 1, 其中指向4的边的权值最小, 所以我们
 * 								 会将这个边放入最小生成树中, 同时将4作为下一个遍历的点, 加入4有三条边, 分别指向5, 6, 1, 权值为3.4, 3.5, 3.6, 
 * 								那么此时的切分中, 其中一个图是0, 4, 另一个图是 剩下的边, 那么此时因为在0->1之间已经有一个权值为3.5的值, 其小于
 * 								4->1中的3.6, 所以这个3.6一定不会成为最小生成树的边
 * 			而第一种实现当中, 我们会把所有的边, 即使不可能成为最小生成树的边都放入堆中, 从而冗余
 * 	实现思路: 
 * 			我们要能够保留权值相对较小的边, 而权值相对较大的边应该不予考虑, 通过一个最小索引堆, 我们在往索引堆中添加顶点对应的边的时候, 此时去
 * 			判断是否在A图(已访问过的图)中指向这些顶点的权值是否是小于当前要添加的边的权值, 如果是, 那么就跳过这条边, 如果不是, 则用当前的边去替换
 * 			索引堆中指向该顶点的边, 如果指向该顶点的边为null, 则直接添加即可, 然后找到索引堆中权值最小的边对应的另外一个顶点, 再以其为顶点去继续
 * 			执行切分操作
 * 	
 * 	与第一种的区别: 我们不再将比指向一个顶点的权值大的边添加到堆中, 同时将权值小的边去替换原来的边, 将原来的边作废, 后面跟第一种思路是一样的, 
 * 						都要通过最小边的另外一个顶点去执行切分操作
 * 
 * 	时间复杂度: O(ElogV)
 * @author 
 */
public class PrimOptimized<T extends Comparable<T>> {
	private Graph<T> graph;					// 图的副本
	private boolean[] visited;				// 是否被访问过
	private ArrayList<Edge<T>> minSpanningTree;	// 存储最小生成树的数组
	
	public PrimOptimized (Graph<T> graph) {
		this.graph = graph;
		this.visited = new boolean[graph.getPeak()];
		this.minSpanningTree = new ArrayList<>();
		
		// 初始化最小生成树
		for (int i = 0; i < visited.length; i ++) {
			visited[i] = false;
		}
		
		generateMinSpanningTree();
	}
	
	public void generateMinSpanningTree () {
		IndexMinHeapOptimized<Edge<T>> heap = new IndexMinHeapOptimized<>(graph.getPeak());
		visited[0] = true;
		int index = 0;
		
		while (true) {
			Iterators iterator = graph.iterator(index);
			while (!iterator.end()) {
				Edge<T> nextEdge = (Edge<T>)iterator.next();
				
				if (visited[nextEdge.getToPeak()]) {
					continue;
				}
				
				Edge<T> oldEdge = heap.getDataByIndex(nextEdge.getToPeak());
				// 如果指向该顶点的边还不存在, 就直接设置上去
				if (oldEdge == null) {
					heap.insert(nextEdge.getToPeak(), nextEdge);
				} else if (nextEdge.getWeight().compareTo(oldEdge.getWeight()) < 0) {
					// 如果新的边的权值比旧的边的权值还小, 就用新的边去替换旧的边
					heap.change(nextEdge.getToPeak(), nextEdge);
				}
			}
			
			// 处理完当前顶点的所有边之后, 取出索引堆中最小的边, 用最小的边去获取下一个切分顶点
			// 如果最小的边不是横切边, 则循环一直进行下去, 直到获取一条横切边为止
			Edge<T> minEdge = heap.extractMin();
			while (visited[minEdge.getToPeak()] && visited[minEdge.getFromPeak()]) {
				minEdge = heap.extractMin();
			}
			
			// 将最小的边作为最小生成树中的一条边
			minSpanningTree.add(minEdge);
			
			index = minEdge.getToPeak();
			visited[index] = true;
			
			if (heap.isEmpty()) {
				return;
			}
		}
	}
	
	/**获取最小生成树的所有边**/
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
