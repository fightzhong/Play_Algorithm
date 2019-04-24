package graph;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import graph.DenseGraph;
import graph.Graph;
import graph.Iterators;
import graph.SparseGraph;
import traverse.WidePriorityTraverse;
import traverse.shortestPath;

public class TestGraph {
	public static void main(String[] args) throws Exception {
		// 对文件testG.txt进行读取
		BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream("Graph/graph/testG.txt")));
		
		
		String str = file.readLine();
		int[] a = handleString(str);
		int[][] arr = new int[a[1] + 1][2];
		int index = 0;
		
		while (str != null) {
			arr[index++] = a;
			str = file.readLine();
			a = handleString(str);
		}
		
		file.close();
	}
	
	public static void traverse (Graph graph, int[][] data) {
		for (int i = 1; i < data.length; i++) {
			graph.addEdge(data[i][0], data[i][1]);
		}
		System.out.println("========开始迭代边========");
		System.out.println(graph.getClass().getName() + " 的边数为: " + graph.getEdge());
		
		// 对每一个顶点都进行迭代
		for (int i = 0; i < data[0][0]; i++) {
			Iterators iterator = graph.iterator(i);
			ArrayList<Integer> arr = new ArrayList<>();
			
			while (!iterator.end()) {
				arr.add(iterator.next());
			}
			
			System.out.println(i + ": " + arr);
		}
	}
	
	public static int[] handleString (String str) {
		if (str == null) {
			return null;
		}
		int[] arr = new int[2];
		
		String[] strArr = str.split(" ");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(strArr[i]);
		}
		
		return arr;
	}
}
