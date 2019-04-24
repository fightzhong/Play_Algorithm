package Heap;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

import mergesort.MergeSortOriginal;

/**
 * 在N个元素中找出前M个元素: 正常的方式是通过对这N个元素进行排序, 然后取出前M个元素, 那么这个排序的时间复杂度就是(依照最快的排序): O(nlogn)
 * 那么我们可以维护一个一个最小堆, 然后不停的将后面的元素插入进去, 这样就可以达到O(nlogM)的时间复杂度 
 * @author zhongshenglong
 *
 */
public class FindDatabeforeM {
	/**时间复杂度: O(nlogn)**/
	public static <T extends Comparable<T>> T[] getEleBeforeM (T[] arr, int M) {
		// 先对这个数组进行快速排序
		arr = new MergeSortOriginal().sort(arr);
		T[] returnArr = (T[])Array.newInstance(arr.getClass().getComponentType(), M);
		
		for (int i = 0; i < M; i++) {
			returnArr[i] = arr[i];
		}
		
		
		return returnArr;
	}
	
	/**时间复杂度: O(nlogM)**/
	public static <T extends Comparable<T>> T[] getEleBeforeMOptimized (T[] arr, int M) {
		// 构造一个最小堆
		MinHeap<T> heap = new MinHeap<T>(arr.length);
		
		for (int i = 0; i < arr.length; i++) {
			heap.insert(arr[i]);
		}
		
		T[] returnArr = (T[])Array.newInstance(arr.getClass().getComponentType(), M);
		
		for (int i = 0; i < M; i++) {
			returnArr[i] = heap.extractMin();
		}
		
		return returnArr;
	}
	
	private static int num = 100000;
	private static int range = 100000;
	private static int M = 20;
	
	public static void main(String[] args) {
		Random ran = new Random();
		
		Integer[] arr = new Integer[num];
		for (int i = 0; i < num; i++) {
			arr[i] = ran.nextInt(range);
		}
		
		System.out.println(Arrays.toString(arr));
		
		long startTime = System.nanoTime();
		Integer[] arr1 = FindDatabeforeM.getEleBeforeM(arr, M);
		long endTime = System.nanoTime();
      double time = (endTime - startTime) / 1000000000.0;
      System.out.println("普通的方式: " + time);
      System.out.println(Arrays.toString(arr1));
      
      startTime = System.nanoTime();
      Integer[] arr2 = FindDatabeforeM.getEleBeforeMOptimized(arr, M);
      endTime = System.nanoTime();
      time = (endTime - startTime) / 1000000000.0;
      System.out.println("优化后的方式: " + time);
      System.out.println(Arrays.toString(arr2));
      
		
		
	}
	
	
}
