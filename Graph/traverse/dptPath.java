package traverse;

import java.util.ArrayList;

import graph.Graph;
import graph.Iterators;

public class dptPath {
	private Graph graph; 						// 图的副本
	private int peak;								// 进行深度优先遍历的顶点
	private boolean[] visited;					// 整个图中已访问的节点
	private ArrayList<Integer> path;			// 遍历的路径
	
	public dptPath (Graph graph, int peak) {
		this.graph = graph; 
		this.peak = peak;
		this.visited = new boolean[graph.getPeak()];
		this.path = new ArrayList<Integer>();
		
		// 初始化visited数组
		for (int i = 0; i < graph.getPeak(); i++) {
			visited[i] = false;
		}
		
		dpt();
	}
	
	private void dpt () {
		// 先将当前顶点设置为已经访问, 并添加到路径中
		path.add(peak);
		visited[peak] = true;
		dpt(peak);
	}
 	
	private void dpt (int peak) {
		// 获取该顶点的迭代器
		Iterators iterator = graph.iterator(peak);
		
		while (!iterator.end()) {
			int next = iterator.next();
			
			if (!visited[next]) {
				path.add(next);
				visited[next] = true;
				dpt(next);
			}
		}
	}
		
	/**判断从顶点peak到w是否存在路径**/
	public boolean hasPath (int w) {
		return path.contains(w);
	}
	
	/**返回顶点peak到w的路径**/
	public String showPath (int w) {
		assert hasPath(w);
		StringBuilder str = new StringBuilder();
		
		for (int i = 0; i <= w; i++) {
			str.append(i);
			if (i < w) {
				str.append(" -> ");
			}
		}
		
		return str.toString();
	}
}



