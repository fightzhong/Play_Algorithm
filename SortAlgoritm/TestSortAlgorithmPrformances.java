import java.util.Random;

import Heap.MinHeap;
import mergesort.MergeSortOptimized;
import quicksort.QuickSort;
import sort.SortInterface;

/**
 * @author 
 */
public class TestSortAlgorithmPrformances {
	private static int sortNumber = 1000000;
	private static int uperBorder = 10000;
	
	public static void main(String[] args) {
		Random ran = new Random();

		Integer[] arr = new Integer[sortNumber];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = ran.nextInt(uperBorder);
		}
		
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = arr.length - i;
//		}
		
		// SelctionSort
//		sortFun(new SelectionSort(), arr);
		
		// InsertionSort
//		sortFun(new InsertionSort(), arr);
		
		// BubbleSort
//		sortFun(new BubbleSort(), arr);
		
		// ShellSort
//		sortFun(new ShellSort(), arr);
		
		// MergeSort
//		sortFun(new mergesort.MergeSortOriginal(), arr);
//		sortFun(new MergeSort(), arr);
		sortFun(new MergeSortOptimized(), arr);
//		sortFun(new MergeSortBottomToUp(), arr);
		
		// QuickSort
		sortFun(new QuickSort(), arr);
		
		
		// HeapSort
		sortFun(new MinHeap<Integer>(), arr);
		
	}
	
	private static <T extends Comparable<T>> void sortFun (SortInterface sort, T[] arr) {
		long startTime = System.nanoTime();
		T[] newArr = sort.sort(arr);
		long endTime = System.nanoTime();

      double time = (endTime - startTime) / 1000000000.0;

		boolean success = true;
		for (int i = 1; i < newArr.length; i++) {
			if (newArr[i - 1].compareTo(newArr[i]) > 0) {
				success = false;
			}
		}
		
		if (success) {
			System.out.println(sort.getClass().getName() + ": 排序成功, 消耗时间: " + time + "s" );
		} else {
			System.out.println(sort.getClass().getName() + ": 排序失败");
		}
	}
	
}
