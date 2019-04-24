package dijkstra;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import prim.Prim;
import prim.PrimOptimized;
import weightgraph.DenseWeightedGraph;
import weightgraph.Edge;
import weightgraph.Graph;
import weightgraph.Iterators;
import weightgraph.SparseWeightedGraph;


public class TestShortestPath {
	public static void main(String[] args) throws Exception {
		// 对文件testG.txt进行读取
		BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream("Graph/dijkstra/testG1.txt")));
		
		
		String str = file.readLine();
		int[] a = handleIntString(str);			// [8, 16]
		double[][] arr = new double[a[1] + 1][2];
		int index = 0;
		
		double[] b = new double[3];
		while (str != null) {
			arr[index++] = b;
			str = file.readLine();
			b = handleDoubleString(str);
		}
		
		SparseWeightedGraph<Double> sparseGraph = new SparseWeightedGraph<>(a[0], true);
		traverse(sparseGraph, arr, a);
		Dijkstra<Double> dijkstra = new Dijkstra<Double>(sparseGraph);
		for (int i = 0; i < a[0]; i ++) {
			System.out.println("0 -> " + i + "的最小路径: " + dijkstra.getShortestPath(i));
			System.out.println("0 -> " + i + "的最小路径的权值: " + dijkstra.getWeight(i));
			System.out.println();
			
		}
		
		file.close();
	}
	
	public static void traverse (Graph graph, double[][] data, int[] a) {
		for (int i = 1; i < data.length; i++) {
			graph.addEdge((int)data[i][0], (int)data[i][1], data[i][2]);
		}
		System.out.println("========开始迭代边========");
		System.out.println(graph.getClass().getName() + " 的边数为: " + graph.getEdge());
		
		// 对每一个顶点都进行迭代
		for (int i = 0; i < a[0]; i++) {
			Iterators iterator = graph.iterator(i);
			ArrayList<Edge> arr = new ArrayList<>();
			
			while (!iterator.end()) {
				arr.add(iterator.next());
			}
			
			System.out.println(i + ": " + arr);
		}
	}
	
	public static int[] handleIntString (String str) {
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
	
	public static double[] handleDoubleString (String str) {
		if (str == null) {
			return null;
		}
		double[] arr = new double[3];
		
		String[] strArr = str.split(" ");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Double.parseDouble(strArr[i]);
		}
		
		return arr;
	}
}
