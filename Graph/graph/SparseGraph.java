package graph;

import java.util.ArrayList;

/**
 * 稀疏图(sparsegraph): 利用邻接表的形式实现
 * 在添加边的时候, 我们需要先判断是否存在这条边, 只有不存在的情况才添加, 同时因为这个contains操作是扫描整个链表 , 所以其是O(n)的时间复杂度
 * 但是在后面的用例中, 避免了平行边的情况, 为了防止此次影响算法的性能, 我们这里不进行这个判断(其实是可以通过其它方式来使得O(1)时间复杂度的)
 * 
 * 其次稀疏图是允许存在自环边的
 * @author 
 */
public class SparseGraph implements Graph{
	private int peak; // 顶点的个数
	private int edge; // 边的个数
	private boolean directed;
	private ArrayList<Integer>[] graph; // 图的信息, 因为顶点个数是固定的, 所以采用数组的方式表示图的顶点, 采用链表的方式来表示每个顶点对应的边的索引
	
	@SuppressWarnings("unchecked")
	public SparseGraph (int peak, boolean directed) {
		this.peak = peak;
		this.edge = 0;
		this.directed = directed;
		this.graph = (ArrayList<Integer>[])new ArrayList[peak];
		
		// 初始化邻接表中的每一个链表
		for (int i = 0; i < graph.length; i++) {
			this.graph[i] = new ArrayList<Integer>();
		}
	}
	
	// 向图中添加一条边
	public void addEdge (int i, int j) {
		assert i >= 0 && i < peak && j >= 0 && j < peak;
	
		// 在添加之前, 我们需要先判断是否存在这条边, 只有不存在的情况才添加, 同时因为这个contains操作是扫描整个链表 , 所以其是O(n)的时间复杂度
		// 但是在后面的用例中, 避免了平行边的情况, 为了防止此次影响算法的性能, 我们这里不进行这个判断(其实是可以通过其它方式来使得O(1)时间复杂度的)
//			if (graph[i].contains(j)) { return; }
			
		// 向顶点i中添加一条指向j的边
		graph[i].add(j);
		
		// 如果不是有向图, 则应该在顶点j中也添加一条指向i的边
		if (!directed) {
			graph[j].add(i);
		}
		
		// 维护边的个数
		edge++;
	}
	
	// 判断两个顶点之间是否存在边
	public boolean hasEdge (int i, int j) {
		assert i >= 0 && i < peak && j >= 0 && j < peak;
		return graph[i].contains(j);
	}
	
	// 获取图中顶点的个数
	public int getPeak () {
		return peak;
	}
	
	// 获取图中表的个数
	public int getEdge () {
		return edge;
	}
	
	public Iterator iterator (int a) {
		return new Iterator(a);
	}
	
	// 迭代器, 获取一个迭代器, 对顶点a进行迭代相邻边
	private class Iterator implements Iterators {
		private int a; // 表示要迭代的顶点
		private int index; // 迭代到当前顶点的第几条边
		
			public Iterator (int a) {
			this.a = a;
			this.index = 0;
		}
		
		public int next () {
			if (index >= graph[a].size()) {
				return -1;
			}
			
			return graph[a].get(index++);
		}
		
		public boolean end () {
			return index >= graph[a].size();
		}
	}
}
