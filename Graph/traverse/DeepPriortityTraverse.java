package traverse;

import java.util.ArrayList;
import java.util.Arrays;

import graph.Graph;
import graph.Iterators;

public class DeepPriortityTraverse {
	private Graph graph;					// 图的副本
	private boolean[] visited;			// 表名一个图中的顶点是否被访问过
	private int count;					// 图的个数
	private ArrayList<Integer> path; // 图的深度优先遍历的路径
	private int[] isGroup;				// 相同值即为同一个图
	
	public DeepPriortityTraverse (Graph graph) {
		this.graph = graph;
		this.visited = new boolean[graph.getPeak()];
		this.count =  0;
		this.path = new ArrayList<Integer>();
		this.isGroup = new int[graph.getPeak()];
		
		//	初始化visited数组, isGroup数组
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
			isGroup[i] = -1;
		}
		
		for (int i = 0; i < graph.getPeak(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				isGroup[i] = count;
				path.add(i);
				dpt(i);
				count++;
			}
		}
	}
	
	private void dpt (int peak) {
		// 获取到顶点的迭代器
		Iterators iterator = graph.iterator(peak);
		
		while (!iterator.end()) {
			int index = iterator.next();
			if (!visited[index]) {
				path.add(index);
				isGroup[index] = count;
				visited[index] = true;
				dpt(index);
			}
		}
	}
	
	/**判断两个顶点是否存在路径(即是否是同一个图)**/
	public boolean isConnected (int i, int j) {
		return isGroup[i] == isGroup[j];
	}
	
	/**返回图的连通通量个数**/
	public int getCount () {
		return count;
	}
	
	/**返回深度优先遍历的过程**/
	public String showTraversePath () {
		StringBuilder str = new StringBuilder();
		
		for (int i = 0; i < path.size(); i++) {
			str.append(path.get(i));
			if (i < graph.getPeak() - 1) {
				str.append(" -> ");
			}
		}
		
		return str.toString();
	}
	
	/**返回图的分类情况**/
	public String getGroupMes () {
		return Arrays.toString(isGroup);
	}
}







