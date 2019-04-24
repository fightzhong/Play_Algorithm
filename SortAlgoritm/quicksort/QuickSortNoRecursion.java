package quicksort;

import java.lang.reflect.Array;
import java.util.Arrays;

import sort.SortInterface;
import sort.Swap;

/**
 * QuickSort(快速排序): 利用非递归的方式实现的, 存在一个缺点
 * 缺点: 对每一个元素在排序的时候都要创建一个新的数组, 空间消耗很大, 同时,
 * 		每一个元素都要将其放在第一个位置, 导致每个元素都造成O(n)的操作
 * 		算法整体被提升到了O(n^2)以上的时间复杂度
 * @author
 */
public class QuickSortNoRecursion implements SortInterface{

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		// get a duplicate array of arr
		arr = Arrays.copyOf(arr, arr.length);
		
		// returnArr用于存放每一个找到位置的元素
		T[] returnArr = (T[])Array.newInstance(arr.getClass().getComponentType(), arr.length);
		
		T[] sortArr;
		// 对整个数组遍历一遍, 使得每一个元素都能找到合适的位置
		for (int i = 0; i < arr.length; i++) {
			
			// 用于位置调整的数组
			sortArr = Arrays.copyOf(arr, arr.length);
			// 每次将用于位置调整的元素放在第一位
			Swap.swap(sortArr, 0, i);
			int littleIndex = 0;
			
			for (int j = 1; j < sortArr.length; j++) {
				
				// 小的情况, 将littleIndex后一位的值, 即比调整的值大的那一列中的第一个值调整到当前遍历值位置, 同时littleIndex 应该 + 1;
				if (sortArr[j].compareTo(sortArr[0]) < 0) {
					Swap.swap(sortArr, j, ++littleIndex);
				} 
				
			}
			
			// 对所有元素遍历过后, littleIndex以左包括其自己都是比当前值小的
			// 以右到bigIndex的都是比当前值大的, 那么littleIndex位置的值就是该值应该放的位置
			while (returnArr[littleIndex] != null) {
				littleIndex++;
			}
			returnArr[littleIndex] = sortArr[0];
		}
		
		return returnArr;
	}
}
