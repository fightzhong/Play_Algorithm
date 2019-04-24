package graph;

/**
 * 稠密图(densegraph): 利用邻接矩阵的方式实现 
 * 利用邻接矩阵实现的无向图, 由于是采用布尔型来判断两个顶点存在边的情况, 所以其避免了平行边的问题, 但是却存在自环边
 * @author 
 */
public class DenseGraph implements Graph {
	private int peak; // n表示图中顶点的个数
	private int edge; // m表示边的个数
	private boolean directed; // directed表示是否是有向图
	private boolean[][] graph; // graph用来存储图的数据
	
	public DenseGraph (int peak, boolean directed) {
		this.peak = peak;
		this.edge = 0;
		this.directed = directed;
		this.graph = new boolean[peak][peak]; // 创建一个n * n的矩阵
		
		// 初始化矩阵, 所有顶点之间都没有连接, 初始化为false
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				graph[i][j] = false;
			}
		}
	}
	
	// 向i, j两个顶点中添加一条边
	public void addEdge (int i, int j) {
		assert i >= 0 && i < peak && j >= 0 && j < peak;
		
		// 只有i -> j之间不存在边的情况, 才要添加一条边
		if (!graph[i][j]) {
			graph[i][j] = true;
			
			// 如果不是有向边, 那么就应该将j i也设置为true
			if (!directed) {
				graph[j][i] = true;
			}
			
			// 维护边的个数
			edge++;
		}		
		
	}
	
	// 判断两个顶点之间是否存在边
	public boolean hasEdge (int i, int j) {
		assert i >= 0 && i < peak && j >= 0 && j < peak;
		return graph[i][j];
	}
	
	// 获取顶点的个数
	public int getPeak () {
		return peak;
	}
	
	// 获取边的条数
	public int getEdge () {
		return edge;
	}
	
	// 对顶点a进行迭代获取其所有的边
	public Iterator iterator (int a) {
		return new Iterator(a);
	}
	
	private class Iterator implements Iterators {
		private int a; // 被迭代的顶点
		private int index; // 当前迭代的顶点的第index条边
		
		public Iterator (int a) {
			this.a = a;
			index = 0;
		}

		@Override
		public int next() {
			while (index < peak) {
				if (graph[a][index]) {
					return index++;
				}
				index++;
			}
			return -1;
		}

		// 当index >= peak时, 此时已经超过了数组的大小, 则表示迭代停止了
		@Override
		public boolean end() {
			for (int i = index; i < peak; i++) {
				if (graph[a][i]) {
					return false;
				}
			}
			return true;
		}
	}
}
