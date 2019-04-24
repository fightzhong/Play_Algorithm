package selectionsort;

import java.util.Arrays;

import sort.SortInterface;
import sort.Swap;

/*
 	时间复杂度: O(n^2)
	SelectionSort选择排序(选择最小或者最大的元素): 
				将当前索引开始到数组末尾中最小的值放在当前索引位置, 我们采用记录最小索引位置的方式来
				减少swap操作, 极大的提高了选择排序的性能
 */ 
/**
 * @author 
 */
public class SelectionSort implements SortInterface {
	
	public static <T> T[] sort () {
		
		return null;
	}
	
	@Override
	public <T extends Comparable<T>> T[] sort (T[] arr) {
		// get a duplicate of sortArr
		arr = Arrays.copyOf(arr, arr.length);
		
		// start to sort
		for (int i = 0; i < arr.length - 1; i++) {
			// 记录当前索引开始往后最小的元素
			int min = i; 
			for (int j = i + 1; j < arr.length; j++) {
				// get the smallest value to index of i gradually
				if (arr[j].compareTo(arr[min]) < 0) {
					min = j;
				}
			}
			Swap.swap(arr, i, min);
		}
		
		return arr;
	}
}
