package traverse;

import java.util.ArrayList;
import java.util.LinkedList;

import graph.Graph;
import graph.Iterators;

public class shortestPath {
	private Graph graph;			// 图的副本
	private int[]	from;			// 每一个顶点的来源
	private boolean[] visited;	// 顶点是否被访问过
	private int peak;				//广度优先遍历的起始顶点
	private int[] laneLen;		// 到达每一个顶点的路径长度
	
	public shortestPath (Graph graph, int peak) {
		this.graph = graph;
		this.peak = peak;
		this.visited = new boolean[graph.getPeak()];
		this.from = new int[graph.getPeak()];
		this.laneLen = new int[graph.getPeak()];
		
		// 初始化visited, int数组
		for (int i = 0; i < graph.getPeak(); i++) {
			visited[i] = false;
			from[i] = -1;
			laneLen[i] = -1;
		}
		
		wpt();
	}
	
	/**对顶点peak进行广度优先遍历**/
	public void wpt () {
		// 创建一个队列
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.addLast(peak);
		
		// 设置顶点peak的基本属性
		visited[peak] = true;
		from[peak] = peak; // 规定遍历的起点是来源于其本身
		laneLen[peak] = 0;
		
		while (!queue.isEmpty()) {
			int index = queue.removeFirst();
			
			Iterators iterator = graph.iterator(index);
			while (!iterator.end()) {
				int next = iterator.next();
				if (!visited[next]) {
					queue.addLast(next);
					visited[next] = true;
					from[next] = index; // 规定遍历的起点是来源于其本身
					laneLen[next] = laneLen[index] + 1;
				}
			}
		}
	}
	
	/**判断顶点peak到w顶点是否存在路径**/
	public boolean hasPath (int w) {
		return visited[w];
	}
	
	/**获取从顶点peak到w顶点的最短路径**/
	public String showPath (int w) {
		if (!hasPath(w)) {
			return null;
		}
		
		ArrayList<Integer> pathArr = new ArrayList<>();
		
		int index = w;
		while (true) {
			pathArr.add(index);
			if (from[index] == index) {
				break;
			}
			index = from[index];
		}
		
		StringBuilder str = new StringBuilder();
		for (int i = pathArr.size() - 1; i >= 0; i--) {
			str.append(pathArr.get(i)); 
			if (i > 0) {
				str.append(" -> ");
			}
		}
		return str.toString();
	}
	
	/**获取最短路径的大小**/
	public int getShortestPathLen (int w) {
		if (!hasPath(w)) {
			return -1;
		}
		
		return laneLen[w];
	}
}
