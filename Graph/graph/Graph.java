package graph;

public interface Graph {
	/**添加边**/
	void addEdge (int a, int b);
	/**判断两个顶点是否有边**/
	boolean hasEdge (int a, int b);
	/**获取顶点的个数**/
	int getPeak ();
	/**获取边的个数**/
	int getEdge ();
	/**获取该图的迭代器**/
	Iterators iterator(int a);
}
