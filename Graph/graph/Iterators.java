package graph;

public interface Iterators {
	int next (); // 下一个具有相邻边的顶点的索引, 如果找到最后都没有找到一个顶点, 即没有一个顶点与当前值形成边, 则返回-1, 否则返回顶点的索引
	boolean end (); // 判断是否迭代结束
	
}
