package weightgraph;

public class DenseWeightedGraph<T extends Comparable<T>> implements Graph<T> {
	private int peak;						// 顶点的个数
	private int edge;						// 边的个数
	private boolean directed;			// 是否是有向边
	private Edge<T>[][] graph;			// 图本身的信息

	@SuppressWarnings("unchecked")
	public DenseWeightedGraph(int peak, boolean directed) {
		this.peak = peak;
		this.edge = 0;
		this.directed = directed;
		this.graph = (Edge<T>[][])new Edge[peak][peak];
		//不用对图的信息进行初始化, 其会默认为null
	}
	
	@Override
	public void addEdge(int a, int b, T width) {
		assert a >= 0 && a < peak && b >= 0 && b < peak;
		
		// 如果已经存在一条边了, 那么就先让边的数量减一, 然后再将其替换为新的边
		if (hasEdge(a, b)) {
			edge--;
		}
		
		graph[a][b] = new Edge<T>(a, b, width);
		
		if (a != b && !directed) {
			graph[b][a] = new Edge<T>(b, a, width);
		}
		
		edge++;
	}

	/**是否存在一条a->b的边**/
	@Override
	public boolean hasEdge(int a, int b) {
		assert a >= 0 && a < peak && b >= 0 && b < peak;
		return graph[a][b] != null ;
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
		private int a;						// 被迭代的顶点
		private int curIndex;			// 当前指针所在的位置
		
		public Iterator (int a) {
			this.a = a;
			this.curIndex = 0;
		}

		@Override
		public Edge<T> next() {
			while (curIndex < peak) {
				if (graph[a][curIndex] != null) {
					return graph[a][curIndex++];
				}
				curIndex++;
			}
			
			return null;
		}

		@Override
		public boolean end() {
			int w = curIndex;
			while (w < peak) {
				if (graph[a][w] != null) {
					return false;
				}
				w++;
			}
			return true;
		}
	}
}





