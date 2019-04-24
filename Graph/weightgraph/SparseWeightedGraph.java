package weightgraph;

import java.util.ArrayList;

public class SparseWeightedGraph<T extends Comparable<T>> implements Graph<T> {
	private int peak;
	private int edge;
	private boolean directed;
	private ArrayList<Edge<T>>[] graph;
	
	@SuppressWarnings("unchecked")
	public SparseWeightedGraph (int peak, boolean directed) {
		this.peak = peak;
		this.directed = directed;
		this.graph = (ArrayList<Edge<T>>[])new ArrayList[peak];
		
		for (int i = 0; i < peak; i++) {
			graph[i] = new ArrayList<Edge<T>>();
		}
	}
	
	@Override
	public void addEdge(int a, int b, T width) {
		assert a >= 0 && b >= 0 && a < peak && b < peak;
		
		// 没有考虑如果a->b已经存在的情况, 因为在稀疏图中判断是否存在一条边需要遍历当前顶点的对应顶点
		graph[a].add(new Edge<T>(a, b, width));
		
		if (!directed) {
			graph[b].add(new Edge<T>(b, a, width));
		}
		
		edge++;
	}

	@Override
	public boolean hasEdge(int a, int b) {
		return graph[a].get(b) != null;
	}

	@Override
	public int getPeak() {
		return peak;
	}

	@Override
	public int getEdge() {
		return edge;
	}

	@Override
	public Iterators iterator(int a) {
		return new Iterator(a);
	}
	
	private class Iterator implements Iterators {
		private int a;					// 迭代的顶点
		private int curIndex;		// 当前正在被迭代的边
		
		public Iterator (int a) {
			this.a = a;
			curIndex = 0;
		}

		@Override
		public Edge<T> next() {
			if (curIndex >= graph[a].size()) {
				return null;
			}
			return graph[a].get(curIndex++);
		}

		@Override
		public boolean end() {
			return curIndex >= graph[a].size() ;
		}
	}
}










