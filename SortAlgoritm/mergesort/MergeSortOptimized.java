package mergesort;

import java.lang.reflect.Array;
import java.util.Arrays;

import insertionsort.InsertionSort;
import sort.SortInterface;

/*
 	优化地方: 
 		<1> 采用泛型擦除的方式来实现返回值为泛型
 				T[] returnArr = (T[])Array.newInstance(arr.getClass().getComponentType(), 1);
 		<2> 当一个数组的所有值小于等于另一个数组的时候, 直接合并就好了, 不用在判断
 		<3> 当分割的数据量少于一个指定的数的时候, 直接使用插入排序, 提高效率
 */
/**
 * @author 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MergeSortOptimized implements SortInterface{
	private static int LEAST_ELE = 10;
	@Override
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		
		arr = (T[]) mergeSort(arr, 0, arr.length - 1);
		
		return arr;
	}
	
	/**对传入的数组的指定索引进行归并排序, 递归算法, 并将排序后的新数组返回**/ 
	private <T extends Comparable> T[] mergeSort (T[] arr, int left, int right) {
		// 递归终止条件, 小于15个元素, 转为插入排序
		if (right - left <= LEAST_ELE) {
			T[] returnArr = Arrays.copyOfRange(arr, left, right);
			return (T[]) new InsertionSort().sort(returnArr, 0, returnArr.length - 1);
		}
		
		int mid =  (right - left) / 2 + left;
		
		// 以中点为界, 分成两半继续分割数组
		T[] a1 = mergeSort(arr, left, mid);
		T[] a2 = mergeSort(arr, mid + 1, right);
		
		// 对数组进行合并, 此时这两个数组都是已经排好了序的
		// 数组一当前被比较的位置
		int a1Index = 0; 
		// 数组二当前被比较的位置
		int a2Index = 0; 
		T[] returnArr = (T[])Array.newInstance(arr.getClass().getComponentType(), a1.length + a2.length);
		// returnArr下一个被插入元素的位置
		int returnArrIndex = 0; 
		
		// 如果其中一个数组里面的元素已经小于另一个数组了, 那么直接合并, 不用进行比较了
		// a1的最后一个 <= a2的第一个
		if (a1[a1.length - 1].compareTo(a2[0]) <= 0) { 	
			for (T t: a1) {
				returnArr[returnArrIndex++] = t;
			}
			
			for (T t: a2) {
				returnArr[returnArrIndex++] = t;
			}
			return returnArr;
		} else if (a2[a2.length - 1].compareTo(a1[0]) <= 0) {
			for (T t: a2) {
				returnArr[returnArrIndex++] = t;
			}
			
			for (T t: a1) {
				returnArr[returnArrIndex++] = t;
			}
			return returnArr;
		}
		
		// 合并判断开始
		while (a1Index < a1.length && a2Index < a2.length) {
			// 如果数组一当前的值大于数组二当前的值, 则将数组二当前的值放入数组中
			if (a1[a1Index].compareTo(a2[a2Index]) > 0) { 
				returnArr[returnArrIndex] = a2[a2Index];
				a2Index++;
			} else {
				returnArr[returnArrIndex] = a1[a1Index];
				a1Index++;
			}
			returnArrIndex++;
		}
		
		if (a1Index >= a1.length && a2Index < a2.length) {
			for (int i = a2Index; i < a2.length; i++) {
				returnArr[returnArrIndex++] = a2[i];
			}
		}
		
		if (a2Index >= a2.length && a1Index < a1.length) {
			for (int i = a1Index; i < a1.length; i++) {
				returnArr[returnArrIndex++] = a1[i];
			}
		}
		
		
		return returnArr;
	}
	
}
