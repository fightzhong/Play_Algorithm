package kruskal;

public class UnionFind {
	private int[] parent;
	private int[] rank;
	
	public UnionFind (int capacity) {
		parent = new int[capacity];
		rank = new int[capacity];
		for (int i = 0; i < capacity; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
	}

	public void union (int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) 
			return;
		
		if (rank[aRoot] < rank[bRoot]) {
			parent[bRoot] = aRoot;
		} else if (rank[bRoot] < rank[aRoot]) {
			parent[aRoot] = bRoot;
		} else { // rank[bRoot] = rank[aRoot]
			parent[aRoot] = bRoot;
			rank[bRoot] += 1;
		}
	}
	
	public boolean isConnected (int a, int b) {
		return find(a) == find(b);
	}
	
	// 该方法用于查找index的根节点
	private int find (int index) {
		// 如果已经是根节点了, 那么就返回该根节点给上一层接收
		if (parent[index] == index) {
			return index;
		}
		
		// 不是根节点的情况, 则将当前值指向其父亲节点指向的根节点
		parent[index] = find(parent[index]);
		
		// 最后一定要将该节点指向的根节点返回给上一层接收
		return parent[index];
	}
	
	public int getSize () {
		return parent.length;
	}
}
