package prim;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import weightgraph.DenseWeightedGraph;
import weightgraph.Edge;
import weightgraph.Graph;
import weightgraph.Iterators;


public class TestMinSpanningTree2 {
	public static void main(String[] args) throws Exception {
		
		
		test("Graph/prim/testG1.txt");
		test("Graph/prim/testG2.txt");
		test("Graph/prim/testG3.txt");
		test("Graph/prim/testG4.txt");
		
		
		
		
		
	}
	
	public static void test (String fileName) throws Exception {
		// 对文件进行读取
		BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		
		String str = file.readLine();
		int[] a = handleIntString(str);			
		double[][] arr = new double[a[1] + 1][2];
		int index = 0;
		
		double[] b = new double[3];
		while (str != null) {
			arr[index++] = b;
			str = file.readLine();
			b = handleDoubleString(str);
		}
		
		// DenseWeightGraph
		DenseWeightedGraph<Double> denseGraph = new DenseWeightedGraph<Double>(a[0], false);
		traverse(denseGraph, arr, a);
		
		long start = System.currentTimeMillis();
		Prim prim = new Prim(denseGraph);
		long end = System.currentTimeMillis();
		System.out.println(fileName + ": lazyPrim->Weight: " + prim.getMinWeight() + ", 花费时间: " + (end - start) + "ms");
		
		start = System.currentTimeMillis();
		PrimOptimized<Double> prim2 = new PrimOptimized<>(denseGraph);
		end = System.currentTimeMillis();
		System.out.println(fileName + ": PrimOptimized->Weight: " + prim2.getMinWeight() + ", 花费时间: " + (end - start) + "ms");
		
		file.close();
//		System.out.println("===================================");
//		SparseWeightedGraph<Double> sparseGraph = new SparseWeightedGraph<Double>(a[0], false);
//		traverse(sparseGraph, arr, a);
//		PrimOptimized<Double> prim2 = new PrimOptimized<>(sparseGraph);
//		System.out.println("SparseGraph: " + fileName + ": Weight: " + prim2.getMinWeight());
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
			
//			System.out.println(i + ": " + arr);
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
