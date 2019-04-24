package weightgraph;

public interface Graph<T extends Comparable<T>> {
	/**在顶点a上添加一条指向顶点b的边, 其中长度为width**/
	void addEdge (int a, int b, T width);
	/**判断两个顶点是否有边**/
	boolean hasEdge (int a, int b);
	/**获取顶点的个数**/
	int getPeak ();
	/**获取边的个数**/
	int getEdge ();
	/**获取该图的迭代器**/
	Iterators iterator(int a);
}
