package mergesort;

import java.util.Arrays;

import sort.SortInterface;

/*
 *	时间复杂度: O(nlogn), 因为要对
 *	归并排序: 将数组通过递归的方式不停的分割成两份, 直到最后只剩下一个元素时即为递归终止条件, 此时向上
 *				回溯, 上一层对只剩下一个元素的数组进行合并, 变成一个有序的数组, 然后再向上回溯, 直到最后
 *				整个数组变成有序的
 *				
 *	问题: 
 *			我们要求传入的数组是泛型并实现了可比较性, 但是由于在创建新数组的时候, 不能创建泛型数组
 *			所以我们需要创建Object[]数组, 此时发现Object -> Comparable是不允许的, 于是转为创建
 *			Comparable[]数组, 又发现Comparable -> T[]是不允许的, 原因是Object类型不一定具有可比较性
 *			实现了Comparable接口不一定是T类型
 *	
 *	解决方法: 泛型擦除
 *			T[] arr = Array.newInstance(arr.getClass.getComponentType(), int length);
 * 
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
/**
 * @author
 */
public class MergeSort implements SortInterface{
	@Override
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		
		arr = (T[]) mergeSort(arr, 0, arr.length - 1);
		
		return arr;
	}
	
	/**对传入的数组的指定索引进行归并排序, 递归算法, 并将排序后的新数组返回**/ 
	private Comparable[] mergeSort (Comparable[] arr, int left, int right) {
		// 递归终止条件, 当只有一个元素的时候
		if (left == right) {
			Comparable[] returnArr = new Comparable[1];
			returnArr[0] = arr[left];
			return returnArr;
		}
		
		int mid =  (right - left) / 2 + left;
		
		// 以中点为界, 分成两半继续分割数组
		Comparable[] a1 = mergeSort(arr, left, mid);
		Comparable[] a2 = mergeSort(arr, mid + 1, right);
		
		// 对数组进行合并, 此时这两个数组都是已经排好了序的
		// 数组一当前被比较的位置
		int a1Index = 0; 
		// 数组二当前被比较的位置
		int a2Index = 0; 
	   // 需要返回的数组
		Comparable[] returnArr = new Comparable[a1.length + a2.length]; 
		// returnArr下一个被插入元素的位置
		int returnArrIndex = 0; 
		
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
