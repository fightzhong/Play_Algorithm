package traverse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import graph.Graph;
import graph.Iterators;

public class WidePriorityTraverse {
	private Graph graph;								// 图的副本
	private int count;								// 图的个数, 连通通量
	private ArrayList<Integer> traversePath;	// 图的广度优先遍历的路径
	private int[]	from;								// 代表每一个节点是从哪个节点遍历而来的
	private int[]	group;							// 相同数字代表同一组
	private boolean[] visited;						// 代表某个顶点是否被访问过
	
	public WidePriorityTraverse (Graph graph) {
		this.graph = graph;
		this.count = 0;
		this.traversePath = new ArrayList<Integer>();
		this.from = new int[graph.getPeak()];
		this.group = new int[graph.getPeak()];
		this.visited = new boolean[graph.getPeak()];
		
		for (int i = 0; i < graph.getPeak(); i++) {
			group[i] = -1;
			visited[i] = false;
		}
		
		// 开始对所有的顶点进行深度优先遍历
		for (int i = 0; i < graph.getPeak(); i++) {
			if (!visited[i]) {
				wpt(i, new LinkedList<Integer>());
				count++;
			}
		}
	}
	
	private void wpt (int peak, LinkedList<Integer> queue) {
		queue.addLast(peak);
		visited[peak] = true;
		from[peak] = -1;  // 设置每一个图遍历的初始点的来源是-1, 则说明其是第一个,其前面没有元素
		
		// 依次取出队列的第一个元素, 同时把该元素的所有相连顶点压入队列
		while (!queue.isEmpty()) {
			int index = queue.removeFirst();
			traversePath.add(index);
			group[index] = count;
			
			// 对该顶点进行迭代, 获取所有与该顶点相连的顶点
			Iterators iterator = graph.iterator(index);
			while (!iterator.end()) {
				int next = iterator.next();
				if (!visited[next]) {
					// 压入队列的同时需要设置该元素已经被访问, 以及该元素的来源是index
					queue.addLast(next);
					visited[next] = true;
					from[next] = index;
				}
			}
		}
	}
	
	/**返回广度优先遍历的过程**/
	public String showWptPath () {
		return traversePath.toString();
	}
	
	/**返回图的连通通量个数**/
	public int getCount () {
		return count;
	}
	 
	/**判断两个顶点是否存在路径(即是否是同一个图)**/
	public boolean isConnected (int i, int j) {
		return group[i] == group[j];
	}
	
	/**返回图的分类情况**/
	public String getGroupMes () {
		return Arrays.toString(group);
	}
	
}	
